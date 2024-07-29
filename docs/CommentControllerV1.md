这个是一个评论的controller

### Controller方法实现

#### 添加评论

```java
@Operation(description = "添加评论")
@PostMapping("/comments/save")
public ResultVO<?> saveComment(@Valid @RequestBody CommentVO commentVO) {
    commentService.saveComment(commentVO);
    return ResultVO.ok();
}
```

- **`@Operation(description = "添加评论")`**：这是一个 Swagger 文档注解，用于描述接口的功能，便于生成 API 文档。
- **`@PostMapping("/comments/save")`**：定义一个 POST 请求的端点，路径为 `/comments/save`，用于添加评论。
- **`@Valid`**：表示对 `commentVO` 参数进行验证。
- **`@RequestBody CommentVO commentVO`**：从请求体中提取 `CommentVO` 对象。
- **`commentService.saveComment(commentVO)`**：调用 `CommentService` 的 `saveComment` 方法保存评论。
- **`ResultVO.ok()`**：返回一个成功的结果包装对象，表示评论已成功添加。

#### 获取评论

```java
@Operation(description = "获取评论")
@GetMapping("/comments")
public ResultVO<List<CommentDTO>> getComments() {
    return ResultVO.ok(commentService.listComments());
}
```

- **`@Operation(description = "获取评论")`**：Swagger 注解，描述获取评论的接口功能。
- **`@GetMapping("/comments")`**：定义一个 GET 请求的端点，路径为 `/comments`，用于获取所有评论。
- **`ResultVO.ok(commentService.listComments())`**：调用 `CommentService` 的 `listComments` 方法获取评论列表，并返回一个成功的结果包装对象。

#### 根据 `commentId` 获取回复

```java
@Operation(description = "根据commentId获取回复")
@GetMapping("/comments/{commentId}/replies")
public ResultVO<List<ReplyDTO>> listRepliesByCommentId(@PathVariable("commentId") Integer commentId) {
    return ResultVO.ok(commentService.listRepliesByCommentId(commentId));
}
```

- **`@Operation(description = "根据commentId获取回复")`**：Swagger 注解，描述根据评论 ID 获取回复的接口功能。
- **`@GetMapping("/comments/{commentId}/replies")`**：定义一个 GET 请求的端点，路径为 `/comments/{commentId}/replies`，用于获取指定评论 ID 的回复。
- **`@PathVariable("commentId") Integer commentId`**：从路径变量中提取评论 ID。
- **`ResultVO.ok(commentService.listRepliesByCommentId(commentId))`**：调用 `CommentService` 的 `listRepliesByCommentId` 方法获取回复列表，并返回一个成功的结果包装对象。

#### 获取前六个评论

```java
@Operation(description = "获取前六个评论")
@GetMapping("/comments/topSix")
public ResultVO<List<CommentDTO>> listTopSixComments() {
    return ResultVO.ok(commentService.listTopSixComments());
}
```

- **`@Operation(description = "获取前六个评论")`**：Swagger 注解，描述获取前六个评论的接口功能。
- **`@GetMapping("/comments/topSix")`**：定义一个 GET 请求的端点，路径为 `/comments/topSix`，用于获取前六个评论。
- **`ResultVO.ok(commentService.listTopSixComments())`**：调用 `CommentService` 的 `listTopSixComments` 方法获取前六个评论，并返回一个成功的结果包装对象。

#### 审核评论

```java
@Operation(description = "审核评论")
@PutMapping("/admin/comments/review")
public ResultVO<?> updateCommentsReview(@Valid @RequestBody ReviewVO reviewVO) {
    commentService.updateCommentsReview(reviewVO);
    return ResultVO.ok();
}
```

- **`@Operation(description = "审核评论")`**：Swagger 注解，描述审核评论的接口功能。
- **`@PutMapping("/admin/comments/review")`**：定义一个 PUT 请求的端点，路径为 `/admin/comments/review`，用于审核评论。
- **`@Valid`**：对 `reviewVO` 参数进行验证。
- **`@RequestBody ReviewVO reviewVO`**：从请求体中提取 `ReviewVO` 对象，用于评论审核。
- **`commentService.updateCommentsReview(reviewVO)`**：调用 `CommentService` 的 `updateCommentsReview` 方法更新评论审核状态。
- **`ResultVO.ok()`**：返回一个成功的结果包装对象，表示评论审核已成功更新。

#### 删除评论

```java
@Operation(description = "删除评论")
@DeleteMapping("/admin/comments")
public ResultVO<?> deleteComments(@RequestBody List<Integer> commentIdList) {
    commentService.removeByIds(commentIdList);
    return ResultVO.ok();
}
```

- **`@Operation(description = "删除评论")`**：Swagger 注解，描述删除评论的接口功能。
- **`@DeleteMapping("/admin/comments")`**：定义一个 DELETE 请求的端点，路径为 `/admin/comments`，用于删除评论。
- **`@RequestBody List<Integer> commentIdList`**：从请求体中提取评论 ID 列表。
- **`commentService.removeByIds(commentIdList)`**：调用 `CommentService` 的 `removeByIds` 方法删除评论。
- **`ResultVO.ok()`**：返回一个成功的结果包装对象，表示评论已成功删除。

## Service方法实现

### 方法实现

#### 保存评论

```java
@Override
public void saveComment(CommentVO commentVO) {
    commentVO.setCommentContent(HTMLUtil.filter(commentVO.getCommentContent()));
    Comment comment = Comment.builder()
            .userId(1) //这里可以根据你实际开发的来写
            .replyUserId(commentVO.getReplyUserId())
            .topicId(commentVO.getTopicId())
            .commentContent(commentVO.getCommentContent())
            .parentId(commentVO.getParentId())
            .type(commentVO.getType())
            .isReview(0) //初始未审核
            .build();
    commentMapper.insert(comment);
}
```

- **`commentVO.setCommentContent(HTMLUtil.filter(commentVO.getCommentContent()))`**：使用 `HTMLUtil` 工具类过滤评论内容中的 HTML 标签。
- **`Comment.builder()`**：使用构建者模式创建一个 `Comment` 实体对象。
- **`userId`**：假设为固定值 `1`，实际开发中应根据当前用户的 ID 动态设置。
- **`isReview(0)`**：评论初始状态为未审核。
- **`commentMapper.insert(comment)`**：调用 `commentMapper` 的 `insert` 方法将评论保存到数据库。

#### 获取所有评论

```java
@Override
public List<CommentDTO> listComments() {
    return commentMapper.listComments();
}
```

- **`commentMapper.listComments()`**：调用 `commentMapper` 的方法获取所有评论，并将其转换为 `CommentDTO` 对象列表。

#### 根据评论 ID 获取回复

```java
@Override
public List<ReplyDTO> listRepliesByCommentId(Integer commentId) {
    return commentMapper.listReplies(Collections.singletonList(commentId));
}
```

- **`Collections.singletonList(commentId)`**：将评论 ID 封装成单元素列表。
- **`commentMapper.listReplies()`**：调用 `commentMapper` 的方法获取指定评论 ID 的所有回复，并将其转换为 `ReplyDTO` 对象列表。

#### 获取前六个评论

```java
@Override
public List<CommentDTO> listTopSixComments() {
    return commentMapper.listTopSixComments();
}
```

- **`commentMapper.listTopSixComments()`**：调用 `commentMapper` 的方法获取前六个评论，并将其转换为 `CommentDTO` 对象列表。

#### 审核评论

```java
@Override
public void updateCommentsReview(ReviewVO reviewVO) {
    List<Comment> comments = reviewVO.getIds().stream().map(item -> Comment.builder()
                    .id(item)
                    .isReview(reviewVO.getIsReview())
                    .build())
            .collect(Collectors.toList());
    this.updateBatchById(comments);
}
```

- **`reviewVO.getIds()`**：获取需要审核的评论 ID 列表。
- **`reviewVO.getIsReview()`**：获取审核状态（例如，审核通过或未通过）。
- **`comments`**：将 ID 列表转换为 `Comment` 对象列表，并设置审核状态。
- **`this.updateBatchById(comments)`**：批量更新评论的审核状态。