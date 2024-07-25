local userId = KEYS[1]
local productId = KEYS[2]
local flag = ARGV[1] -- 1：点赞 0：取消点赞


if flag == '1' then
  -- 用户set添加商品并商品点赞数加1
  if redis.call('SISMEMBER', userId, productId) == 0 then
    redis.call('SADD', userId, productId)
    redis.call('INCR', productId)
  end
else
  -- 用户set删除商品并商品点赞数减1
  redis.call('SREM', userId, productId)
  local oldValue = tonumber(redis.call('GET', productId))
  if oldValue and oldValue > 0 then
    redis.call('DECR', productId)
  end
end

return 1

