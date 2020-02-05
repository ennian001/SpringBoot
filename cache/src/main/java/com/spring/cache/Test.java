package com.spring.cache;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        method1();
        method2();
    }

    private static void method1() {
        Instant now = Instant.now();
        int MAX = 0;
        String a =  "aaa aa aaa aaaa  aaaaaa  aaa";
        String[] s = a.split(" ");
        for (int i = 0; i < s.length; i++) {
            if (MAX<s[i].length()){
                MAX = s[i].length();
            }
        }
        Instant end = Instant.now();
        System.out.println("最长的长度为:"+MAX+"执行时间："+ Duration.between(now,end).toMillis());
    }

    private static void method2(){
        Instant now = Instant.now();

        String a =  "aaa aa aaa aaaa  aaaaaa  aaa";
        int MAX = 0;
        while (true){
            int i1 = a.indexOf(" ");
            if (i1<0){
                break;
            }
            if (MAX<a.substring(0,i1).length()){
                MAX=a.substring(0,i1).length();
            }
            a = a.substring(i1+1);
        }
        Instant end = Instant.now();
        long l = Duration.between(now, end).toMillis();
        System.out.println("最长的长度为:"+MAX+"执行时间："+ l );
    }
}
