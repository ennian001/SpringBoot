package io.spring.study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
public class LockAspect {
    //io.spring.study.service包下的任意类的任意方法的不限参数与返回值
    @Pointcut("execution(public * io.spring.study.service.*.*(..))")
    public void lockAspect(){}

    @Around("lockAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!true){
            System.out.println("无锁方法执行");
            Object proceed = joinPoint.proceed();
            return proceed;
        }else {
            System.out.println("有锁方法不执行");
            return null;
        }

    }






}
