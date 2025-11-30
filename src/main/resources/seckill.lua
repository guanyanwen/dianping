-- 1.参数
local voucherId = ARGV[1]
local userId = ARGV[2]
local orderId = ARGV[3]

-- 2.数据key
local stockKey = 'seckill:stock:' .. voucherId
local orderKey = 'seckill:order:' .. voucherId

-- 3.判断库存是否充足（修复nil问题 + 防止负库存）
local stock = redis.call('get', stockKey)
if not stock or tonumber(stock) <= 0 then
    return 1
end

-- 4.一人一单判断
if redis.call('sismember', orderKey, userId) == 1 then
    return 2
end

-- 5.扣库存（保证不会扣成负数）
redis.call('incrby', stockKey, -1)

-- 6.下单（保存用户）
redis.call('sadd', orderKey, userId)

-- 7.发送消息到stream队列
redis.call('xadd', 'stream.orders', '*', 'userId', userId, 'voucherId', voucherId, 'id', orderId)

return 0