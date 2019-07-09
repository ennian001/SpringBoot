package com.spring.cache.config;

import com.spring.cache.bean.Department;
import com.spring.cache.bean.Employee;
import com.spring.cache.service.DeptService;
import com.spring.cache.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;
import java.util.List;

@Configuration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }
    //配置不同的redisTemplate 否则不同的对象不能反序列化成功
    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    //CacheManagerCustomizers 在RedisCacheConfiguration中用来定义定制一些缓存的默认规则
    //@ConditionalOnMissingBean({CacheManager.class})  如果没有这个类
    /**@Primary 两个cacheManager需要指定一个默认的  不同的cacheManager使用不同的配置 ,因为是默认的可以不指定
     * @see EmployeeService
     */
    @Primary
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object,Employee> employeeRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(employeeRedisTemplate);
        //使用前缀，默认会将cacheName做为前缀(emp:1)
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    /**
     *  @see DeptService
     * @param
     * @return
     */
    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object,Department> departmentRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(departmentRedisTemplate);
        //使用前缀，默认会将cacheName做为前缀(emp:1)
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
