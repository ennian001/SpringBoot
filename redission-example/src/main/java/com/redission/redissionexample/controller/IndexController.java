package com.redission.redissionexample.controller;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/testContoller")
public class IndexController {

    @Autowired
    RedissonClient redisson ;

    @Autowired
    RedisTemplate redisTemplate ;

    /**
     * 分布式锁测试
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        //1、获取锁
        RLock lock = redisson.getLock("my-lock");
        //2、加锁 //阻塞式等待
        // redission 看门狗原理，实现锁的自动续期，运行期间自动给锁续上新的30s，不用担心业务时间长，锁自动过期被删掉
        // 加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动删除
        /**
         * 开门狗原理
         * 如果未指定锁的过期时间，就使用30*1000【lockwatchdogTimeout】看门狗的默认时间 ；
         * 只要占锁成功，就会启动一个定时任务【重新给锁设置过期时间，新的过期时间就是看门狗的默认时间】，每隔十秒都会自动再续期，续成满的看门狗时间
         * 定时任务触发时间：internalLockLeaseTime【看门狗时间】/3 =10s
         */
        lock.lock();
        // 加锁以后10秒钟自动解锁
        // 无需调用unlock方法手动解锁
        // lock.lock(10, TimeUnit.SECONDS);

        //最佳实战
        //使用lock.lock(10, TimeUnit.SECONDS); 省掉了整个续期操作 。 手动解锁
        try {
            System.out.println("加锁成功，执行业务..."+Thread.currentThread().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //3、解锁
            System.out.println("释放锁..."+Thread.currentThread().getId());
            lock.unlock();
        }
        return "hello" ;
    }

    /**
     * 改数据加写锁，读数据加读锁
     * 需求：保证一定能读到最新的数据，修改期间，写锁是一个排他锁（独享锁、互斥锁）。读锁是一个共享锁
     * 写锁 没释放读就必须等待
     * 写+读：等待写锁释放
     * 写+写：阻塞方式
     * 读+写：有读锁，写也需要等待 。
     * 读+读：相当于无锁，并发读，只会在redis中记录好，所有当前的读锁。他们都需要加锁成功。
     * 只要有写存在，都必须等待
     * @return
     */
    @GetMapping("/write")
    public String writeValue(){
        String s = "" ;
        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        RLock rLock = lock.writeLock();
        try {
            //改数据加写锁
            rLock.lock();
            s = UUID.randomUUID().toString();
            Thread.sleep(30000);
            redisTemplate.opsForValue().set("writeValue",s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }
        return s ;
    }
    @GetMapping("/read")
    public String wreadValue(){
        String s = "" ;
        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        RLock rLock = lock.readLock();
        try {
            //改数据加写锁
            rLock.lock();
            s = UUID.randomUUID().toString();
            s = (String) redisTemplate.opsForValue().get("writeValue");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }
        return s ;
    }

    /**
     * 闭锁
     * 放假，锁门
     * 5个班全部锁完才可以锁大门
     */
    @GetMapping("/lockDoor")
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.trySetCount(5);
        //等待闭锁都完成
        door.await();
        return "放假了...." ;
    }
    @GetMapping("/gogogo/{id}")
    public String gogogo(@PathVariable("id") Long id){
        RCountDownLatch door = redisson.getCountDownLatch("door");
        door.countDown();
        return id + "班的人都走了" ;
    }

    /**
     * 信号量：可以限流，一个服务可以获取一个信号量
     * 车库停车
     * 3车位
     */
    //停车
    @GetMapping("/park")
    public String park() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
        // 获取一个信号 ， 获取一个值，占一个车位
        //阻塞式获取
        park.acquire();
        //非阻塞式，返回true/false
        boolean tryAcquire = park.tryAcquire();
        if (tryAcquire){
            //执行业务
        }else {
            return "流量过大";
        }
        return "ok" ;
    }
    @GetMapping("/go")
    public String go() throws InterruptedException {
        RSemaphore park = redisson.getSemaphore("park");
        // 获取一个信号 ， 获取一个值，占一个车位
        park.release();
        return "ok" ;
    }
}
