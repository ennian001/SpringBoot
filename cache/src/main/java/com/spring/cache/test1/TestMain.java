package com.spring.cache.test1;

public class TestMain {
    public static void main(String[] args) {
        Biaodan ejfh = new EjfhBd();

        BiaodanMain biaodanMain = new BiaodanMain(ejfh);
        biaodanMain.save("ejfh");


        Biaodan zhfw = new ZhfwBd();
        BiaodanMain zhfwMain = new BiaodanMain(zhfw);
        zhfwMain.save("zhfw");

        Biaodan zhsw = new ZhswBd();
        BiaodanMain zhswMain = new BiaodanMain(zhsw);
        zhswMain.save("zhsw");

        BiaodanMain testMain = new BiaodanMain(new Biaodan() {
            @Override
            public void save(String title) {
                System.out.println("随便保存到mysql"+title);
            }
        });


    }
}
