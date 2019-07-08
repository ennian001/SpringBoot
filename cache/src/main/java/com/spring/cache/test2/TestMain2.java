package com.spring.cache.test2;

public class TestMain2 {
    public static void main(String[] args) {
        ZhswBiaodan zhswBiaodan = new ZhswBiaodan();
        zhswBiaodan.setNo("no1");

//        ZhswOperMain zhswOperMain = new ZhswOperMain(new RedisNewZhswOper());
//        zhswOperMain.operation(zhswBiaodan);


        ZhswOperMain z1 = new ZhswOperMain(new ZhswOper() {
            @Override
            public void operation(ZhswBiaodan zhswBiaodan) {
                System.out.println("保存到文件系统");
            }
        });
        z1.operation(zhswBiaodan);


        ZhswOperMain z2 = new ZhswOperMain(new ZhswOper() {
            @Override
            public void operation(ZhswBiaodan zhswBiaodan) {
                System.out.println("保存ehcache");
            }
        });
        z2.operation(zhswBiaodan);


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
