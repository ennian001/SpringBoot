package io.spring.study.aspect;

import io.spring.study.annotation.SynLock;
import io.spring.study.service.RedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(0)
public class LockAspect {
    //io.spring.study.service包下的任意类的任意方法的不限参数与返回值
    // 并且
    // 匹配持有 当前注解的方法
    @Pointcut("execution(public * io.spring.study.service.*.*(..)) && @annotation(io.spring.study.annotation.SynLock)")
    public void lockAspect(){}

    @Autowired
    RedisService redisService;

    @Around("lockAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SynLock annotation = method.getAnnotation(SynLock.class);
        String key = annotation.synKey();
        long seconds = annotation.seconds();
        boolean lock = redisService.lock(key, seconds);
        if (lock){
            System.out.println("无锁,并上锁方法执行");
            Object proceed = joinPoint.proceed();
            return proceed;
        }else {
            System.out.println("有锁方法不执行");
            return null;
        }

    }
}
