下面来介绍我们的登录接口

这个来源于鱼皮的开源项目

首先是sql表结构如下：

![image-20240725150523110](https://xiaou-1305448902.cos.ap-nanjing.myqcloud.com/img/202407251505234.png)

之后我们来一个一个去看具体方法

## 用户注册

### 方法实现

#### 1. 检查请求参数是否为空

```java
if (userRegisterRequest == null) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR);
}
```

- 如果 `userRegisterRequest` 为空，则抛出参数错误异常 `BusinessException`。

#### 2. 提取用户账号和密码

```java
String userAccount = userRegisterRequest.getUserAccount();
String userPassword = userRegisterRequest.getUserPassword();
String checkPassword = userRegisterRequest.getCheckPassword();
```

- 从 `userRegisterRequest` 中提取用户账号 `userAccount`、用户密码 `userPassword` 和确认密码 `checkPassword`。

#### 3. 检查提取的参数是否为空

```java
if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
    return null;
}
```

- 使用 `StringUtils.isAnyBlank` 方法检查提取的参数是否为空，如果有任一参数为空，则返回 `null`。

#### 4. 调用用户服务进行注册

```java
long result = userService.userRegister(userAccount, userPassword, checkPassword);
```

- 调用 `userService` 中的 `userRegister` 方法进行用户注册，并将结果存储在 `result` 变量中。

### service层实现

我们service层实现了包括参数校验、密码加密、数据库操作等步骤



#### 1. 参数校验

```java
if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
}
if (userAccount.length() < 4) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
}
if (userPassword.length() < 8 || checkPassword.length() < 8) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
}
if (!userPassword.equals(checkPassword)) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
}
```

- 使用 `StringUtils.isAnyBlank` 方法检查用户账号、用户密码和确认密码是否为空，如果有任一参数为空，则抛出 `BusinessException` 异常，提示 "参数为空"。
- 检查用户账号长度是否小于 4，如果是，则抛出 `BusinessException` 异常，提示 "用户账号过短"。
- 检查用户密码和确认密码长度是否小于 8，如果是，则抛出 `BusinessException` 异常，提示 "用户密码过短"。
- 检查用户密码和确认密码是否相同，如果不同，则抛出 `BusinessException` 异常，提示 "两次输入的密码不一致"。

#### 2. 同步检查账户是否重复

```java
synchronized (userAccount.intern()) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    long count = this.baseMapper.selectCount(queryWrapper);
    if (count > 0) {
        throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
    }
    // 2. 加密
    String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    // 3. 插入数据
    User user = new User();
    user.setUserAccount(userAccount);
    user.setUserPassword(encryptPassword);
    boolean saveResult = this.save(user);
    if (!saveResult) {
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
    }
    return user.getId();
}
```

- 使用 `synchronized` 关键字对 `userAccount` 进行同步操作，确保多线程环境下账号唯一性检查的原子性。
- 创建 `QueryWrapper` 对象并设置查询条件为 `userAccount`。
- 调用 `this.baseMapper.selectCount` 方法查询数据库中是否已有相同账号，如果存在则抛出 `BusinessException` 异常，提示 "账号重复"。

#### 3. 密码加密

```java
String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
```

- 使用 `DigestUtils.md5DigestAsHex` 方法将用户密码进行 MD5 加密，并添加盐值 `SALT` 增强密码安全性。

#### 4. 插入数据

```java
User user = new User();
user.setUserAccount(userAccount);
user.setUserPassword(encryptPassword);
boolean saveResult = this.save(user);
if (!saveResult) {
    throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
}
return user.getId();
```

- 创建 `User` 对象并设置用户账号和加密后的密码。
- 调用 `this.save` 方法将用户信息保存到数据库中，如果保存失败则抛出 `BusinessException` 异常，提示 "注册失败，数据库错误"。
- 返回新注册用户的 ID。

## 用户登录

### 方法实现

#### 1. 检查请求参数是否为空

```java
if (userLoginRequest == null) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR);
}
```

- 如果 `userLoginRequest` 为空，则抛出参数错误异常 `BusinessException`。

#### 2. 提取用户账号和密码

```java
String userAccount = userLoginRequest.getUserAccount();
String userPassword = userLoginRequest.getUserPassword();
```

- 从 `userLoginRequest` 中提取用户账号 `userAccount` 和用户密码 `userPassword`。

#### 3. 检查提取的参数是否为空

```java
if (StringUtils.isAnyBlank(userAccount, userPassword)) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR);
}
```

- 使用 `StringUtils.isAnyBlank` 方法检查提取的参数是否为空，如果有任一参数为空，则抛出 `BusinessException` 异常。

#### 4. 调用用户服务进行登录

```java
LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
```

- 调用 `userService` 中的 `userLogin` 方法进行用户登录，并将结果存储在 `loginUserVO` 变量中。

### service层实现

#### 1. 校验

```java
if (StringUtils.isAnyBlank(userAccount, userPassword)) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
}
if (userAccount.length() < 4) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
}
if (userPassword.length() < 8) {
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
}
```

- 使用 `StringUtils.isAnyBlank` 方法检查用户账号和密码是否为空，如果有任一参数为空，则抛出 `BusinessException` 异常，提示 "参数为空"。
- 检查用户账号长度是否小于 4，如果是，则抛出 `BusinessException` 异常，提示 "账号错误"。
- 检查用户密码长度是否小于 8，如果是，则抛出 `BusinessException` 异常，提示 "密码错误"。

#### 2. 密码加密

```java
String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
```

- 使用 `DigestUtils.md5DigestAsHex` 方法将用户密码进行 MD5 加密，并添加盐值 `SALT` 增强密码安全性。

#### 3. 查询用户是否存在

```java
QueryWrapper<User> queryWrapper = new QueryWrapper<>();
queryWrapper.eq("userAccount", userAccount);
queryWrapper.eq("userPassword", encryptPassword);
User user = this.baseMapper.selectOne(queryWrapper);
```

- 创建 `QueryWrapper` 对象并设置查询条件为 `userAccount` 和加密后的 `userPassword`。
- 调用 `this.baseMapper.selectOne` 方法根据查询条件从数据库中查找用户信息，并将结果存储在 `user` 变量中。

#### 4. 用户不存在处理

```java
if (user == null) {
    log.info("user login failed, userAccount cannot match userPassword");
    throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
}
```

- 如果 `user` 为空，记录日志并抛出 `BusinessException` 异常，提示 "用户不存在或密码错误"。

#### 5. 记录用户的登录态

```java
request.getSession().setAttribute(USER_LOGIN_STATE, user);
```

- 使用 `request.getSession().setAttribute` 方法将用户信息存储在会话中，记录用户的登录态。

#### 6. 返回用户信息视图对象

```java
return this.getLoginUserVO(user);
```

- 调用 `getLoginUserVO` 方法将用户信息转换为 `LoginUserVO` 对象，并返回该对象。

### 辅助方法

#### getLoginUserVO 方法

```java
@Override
public LoginUserVO getLoginUserVO(User user) {
    if (user == null) {
        return null;
    }
    LoginUserVO loginUserVO = new LoginUserVO();
    BeanUtils.copyProperties(user, loginUserVO);
    return loginUserVO;
}
```

- 检查 `user` 是否为空，如果为空则返回 `null`。
- 创建 `LoginUserVO` 对象并使用 `BeanUtils.copyProperties` 方法将 `user` 对象的属性复制到 `loginUserVO` 对象中。
- 返回 `loginUserVO` 对象。

## 其他

获得当前用户和用户注销代码都很简单 因为我们在登录的时候把用户信息存储到session中 所以这些操作就行一个session的查找 删除就可以了。这里就不做过多的讲解