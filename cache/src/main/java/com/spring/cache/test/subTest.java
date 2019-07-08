package com.spring.cache.test;

import java.util.HashMap;

public class subTest extends Test {

    public static void main(String[] args) {
        String s1 = "zhfw";
        String s2 = "zhsw";
        String s3 = "ejfhfw";
        String s4 = "科技部发文";
        String s5 = "北京发文";
    }

    public static void subsave(HashMap<String, String> stringStringHashMap) {
        stringStringHashMap.entrySet().parallelStream().forEach(af -> {
            System.out.println(af.getValue());
        });
    }


}

