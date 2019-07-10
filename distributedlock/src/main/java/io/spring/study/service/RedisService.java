package io.spring.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Protocol;
import redis.clients.util.SafeEncoder;

@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public boolean lock(final String key , final long ex){
        Boolean b = (Boolean) stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer valueSerializer = stringRedisTemplate.getValueSerializer();
                RedisSerializer keySerializer = stringRedisTemplate.getKeySerializer();
                Object obj = connection.execute("set", keySerializer.serialize(key),
                        valueSerializer.serialize("lock"),
                        SafeEncoder.encode("NX"),
                        SafeEncoder.encode("EX"),
                        Protocol.toByteArray(ex));
                return obj != null;
            }

        });
        return b;
    }

    String get(String key , long ex){
        String s = stringRedisTemplate.opsForValue().get(key);
        return s;
    }
}
