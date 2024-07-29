-这个是一个广告管理的controller

首先我们来讲解controller层

### Controller方法及其注解

#### 创建广告

```java
@Operation(description = "添加广告")
@RequestMapping(value = "/create", method = RequestMethod.POST)
@ResponseBody
public CommonResult create(@RequestBody SmsHomeAdvertise advertise) {
    int count = advertiseService.create(advertise);
    if (count > 0)
        return CommonResult.success(count);
    return CommonResult.failed();
}
```

- `@Operation(description = "添加广告")`：用于 OpenAPI 文档生成，描述该方法的功能。
- `@RequestMapping(value = "/create", method = RequestMethod.POST)`：定义 URL 路径和 HTTP 方法。
- `@RequestBody`：将请求体中的 JSON 数据映射到 `SmsHomeAdvertise` 对象。
- `CommonResult`：用于包装响应结果，`success` 方法用于返回成功结果，`failed` 方法用于返回失败结果。

#### 删除广告

```java
@Operation(description = "删除广告")
@RequestMapping(value = "/delete", method = RequestMethod.POST)
@ResponseBody
public CommonResult delete(@RequestParam("ids") List<Long> ids) {
    int count = advertiseService.delete(ids);
    if (count > 0)
        return CommonResult.success(count);
    return CommonResult.failed();
}
```

- `@RequestParam("ids")`：从请求参数中获取广告 ID 列表进行删除操作。

#### 修改广告状态

```java
@Operation(description = "修改上下线状态")
@RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
@ResponseBody
public CommonResult updateStatus(@PathVariable Long id, Integer status) {
    int count = advertiseService.updateStatus(id, status);
    if (count > 0)
        return CommonResult.success(count);
    return CommonResult.failed();
}
```

- `@PathVariable Long id`：从 URL 路径中获取广告 ID。
- `Integer status`：从请求体中获取广告状态，用于更新广告的上线/下线状态。

#### 获取广告详情

```java
@Operation(description = "获取广告详情")
@RequestMapping(value = "/{id}", method = RequestMethod.GET)
@ResponseBody
public CommonResult<SmsHomeAdvertise> getItem(@PathVariable Long id) {
    SmsHomeAdvertise advertise = advertiseService.getItem(id);
    return CommonResult.success(advertise);
}
```

- `@PathVariable Long id`：从 URL 路径中获取广告 ID，获取广告的详细信息。

#### 修改广告信息

```java
@Operation(description = "修改广告")
@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
@ResponseBody
public CommonResult update(@PathVariable Long id, @RequestBody SmsHomeAdvertise advertise) {
    int count = advertiseService.update(id, advertise);
    if (count > 0)
        return CommonResult.success(count);
    return CommonResult.failed();
}
```

- `@PathVariable Long id`：从 URL 路径中获取广告 ID。
- `@RequestBody`：将请求体中的 JSON 数据映射到 `SmsHomeAdvertise` 对象，用于更新广告的信息。

#### 分页查询广告

```java
@Operation(description = "分页查询广告")
@RequestMapping(value = "/list", method = RequestMethod.GET)
@ResponseBody
public CommonResult<CommonPage<SmsHomeAdvertise>> list(@RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "type", required = false) Integer type,
                                                       @RequestParam(value = "endTime", required = false) String endTime,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    List<SmsHomeAdvertise> advertiseList = advertiseService.list(name, type, endTime, pageSize, pageNum);
    return CommonResult.success(CommonPage.restPage(advertiseList));
}
```

- `@RequestParam`：从请求参数中获取分页查询的条件（广告名称、类型、结束时间、每页大小和当前页码）。
- `CommonPage.restPage(advertiseList)`：将广告列表包装成分页格式的响应结果。



之后来看service层

### Service方法实现

#### 创建广告

```java
@Override
public int create(SmsHomeAdvertise advertise) {
    advertise.setClickCount(0);
    advertise.setOrderCount(0);
    return advertiseMapper.insert(advertise);
}
```

- `create` 方法用于插入新的广告记录。
- 在插入之前，将广告的 `clickCount` 和 `orderCount` 初始化为 `0`。
- 调用 `advertiseMapper.insert(advertise)` 将广告保存到数据库。

#### 删除广告

```java
@Override
public int delete(List<Long> ids) {
    SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
    example.createCriteria().andIdIn(ids);
    return advertiseMapper.deleteByExample(example);
}
```

- `delete` 方法用于根据广告 ID 列表删除广告。
- 使用 `SmsHomeAdvertiseExample` 构建查询条件，并调用 `advertiseMapper.deleteByExample(example)` 删除符合条件的记录。

#### 更新广告状态

```java
@Override
public int updateStatus(Long id, Integer status) {
    SmsHomeAdvertise record = new SmsHomeAdvertise();
    record.setId(id);
    record.setStatus(status);
    return advertiseMapper.updateByPrimaryKeySelective(record);
}
```

- `updateStatus` 方法用于更新广告的状态。
- 创建一个 `SmsHomeAdvertise` 对象，仅设置 ID 和状态，并调用 `advertiseMapper.updateByPrimaryKeySelective(record)` 更新数据库中对应的记录。

#### 获取广告详情

```java
@Override
public SmsHomeAdvertise getItem(Long id) {
    return advertiseMapper.selectByPrimaryKey(id);
}
```

- `getItem` 方法用于根据广告 ID 获取广告详细信息。
- 调用 `advertiseMapper.selectByPrimaryKey(id)` 从数据库中查询广告记录。

#### 更新广告信息

```java
@Override
public int update(Long id, SmsHomeAdvertise advertise) {
    advertise.setId(id);
    return advertiseMapper.updateByPrimaryKeySelective(advertise);
}
```

- `update` 方法用于更新广告的信息。
- 将传入的广告对象的 ID 设置为方法参数中的 ID，然后调用 `advertiseMapper.updateByPrimaryKeySelective(advertise)` 更新数据库中的记录。

#### 分页查询广告

```java
@Override
public List<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
    PageHelper.startPage(pageNum, pageSize);
    SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
    SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
    if (!StrUtil.isEmpty(name)) {
        criteria.andNameLike("%" + name + "%");
    }
    if (type != null) {
        criteria.andTypeEqualTo(type);
    }
    if (!StrUtil.isEmpty(endTime)) {
        String startStr = endTime + " 00:00:00";
        String endStr = endTime + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        try {
            start = sdf.parse(startStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date end = null;
        try {
            end = sdf.parse(endStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (start != null && end != null) {
            criteria.andEndTimeBetween(start, end);
        }
    }
    example.setOrderByClause("sort desc");
    return advertiseMapper.selectByExample(example);
}
```

- `list` 方法用于分页查询广告。
- 使用 `PageHelper.startPage(pageNum, pageSize)` 设置分页参数。
- 根据查询条件（广告名称、类型、结束时间）构建查询条件：
  - `name`：广告名称模糊匹配。
  - `type`：广告类型精确匹配。
  - `endTime`：广告结束时间范围查询。
- 设置排序条件（按 `sort` 字段降序排列）。
- 调用 `advertiseMapper.selectByExample(example)` 执行查询，并返回结果。

### 总结

- 该服务类实现了 `SmsHomeAdvertiseService` 接口，负责广告的业务逻辑。
- 使用了 `SmsHomeAdvertiseMapper` 进行数据库操作，`SmsHomeAdvertiseExample` 用于构建查询条件。
- `PageHelper` 用于分页查询。
- 日期格式化和处理在 `list` 方法中完成，确保能正确处理广告的结束时间查询。