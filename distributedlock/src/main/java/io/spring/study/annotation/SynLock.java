package io.spring.study.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SynLock {
    /**
     *  锁的key
     */
    String synKey();
    /**
     * 默认锁的时间
     * @return
     */
    long seconds() default 300;
}
