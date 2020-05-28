package com.spring.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class PieplineRedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
     *
     * @param key
     * @param value
     * @param score
     */
    public void add(String key, String value, double score) {
        stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * //拿指定key的不同分数段的数量，mins和maxs时一一对应的
     *  //比如mins[1,100,1000]、max[99,999,2000],那么就是拿1-99，100-999，1000-2000
     *  //各个分数段的数量，而且时按照顺序返回的，返回的类型是Long类型的，所以使用的时候
     *  //要将我这里返回的Object类型强制转换成Long就行
     * @param key
     * @param mins
     * @param maxs
     * @return
     */
    public List<Object> zSetCountByScoreRange(String key, List<Double> mins, List<Double> maxs){
        try {
            RedisCallback callback = new RedisCallback() {
                @Override
                public Double doInRedis(
                        RedisConnection connection)
                        throws DataAccessException {
                    int i = 0;
                    for(Double min : mins){
                        if(min != null){
                            connection.zCount(key.getBytes(),min,maxs.get(i));
                        }
                        i++;
                    }
                    return null;
                }
            };
            return stringRedisTemplate.executePipelined(callback);
        } catch (Exception e) {
            System.out.println("zRevRangeByScore Exception: "+e);
        }
        return null;
    }

    public void remove(String key , String value){
        stringRedisTemplate.opsForZSet().remove(key,value);
    }

    /**
     * score的增加or减少 zincrby
     *
     * @param key
     * @param value
     * @param score
     */
    public Double incrScore(String key, String value, double score) {
        return stringRedisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     *  查询value对应的score
     */
    public Double scroe(String key , String value){
        return stringRedisTemplate.opsForZSet().score(key,value);
    }

    /**
     *  获取value在集合中的排名
     */
    public Long rank(String key , String value){
        return stringRedisTemplate.opsForZSet().rank(key,value);
    }

    /**
     * 集合大小
     */
    public Long size(String key){
        return stringRedisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 根据score的值，来获取满足条件的集合  zrangebyscore
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> sortRange(String key, int min, int max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange
     *
     * 返回有序的集合，score小的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> range(String key, int start, int end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScore(String key, int start, int end) {
        Set<ZSetOperations.TypedTuple<String>> scores = stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
//        Iterator<ZSetOperations.TypedTuple<String>> iterator = scores.iterator();
//        while (iterator.hasNext()){
//            ZSetOperations.TypedTuple<String> next = iterator.next();
//            Double score = next.getScore();
//            String value = next.getValue();
//        }
        return scores;
    }



}
