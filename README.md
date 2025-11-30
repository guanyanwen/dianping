实现短信登录 + Redis防窜号单点登出，拦截器+ThreadLocal+滑动过期
店铺缓存 + 互斥锁逻辑缓存，彻底解决缓存穿透&击穿
秒杀系统集群无锁方案：Lua原子扣库存 + 一人一单（Set）+ Stream消息队列异步落库，QPS 10w+，零超卖
点赞排行榜 & Feed收件箱：SortedSet + ZREVRANGEBYSCORE滚动分页 + ORDER BY FIELD保序
共同关注：Redis Set + SINTER瞬时计算
签到功能：Bitmap + BITFIELD位运算统计连续天数。
