package com.spring.cache.test;

public class Test {

    public static void main(String[] args) {
        String s1 = "zhfw";
        String s2 = "zhsw";
        String s3 = "ejfhfw";
        String s4 = "科技部发文";
        String s5 = "北京发文";
        //static
        Real.save(s2);
    }

    public static void save(String string){
        if ("zhfw".equals(string)){
            System.out.println("总行发文流程");
        }
        if ("zhsw".equals(string)){
            System.out.println("总行收文流程");
        }
        if ("ejfhfw".equals(string)){
            System.out.println("二级分行");
        }
    }

}

