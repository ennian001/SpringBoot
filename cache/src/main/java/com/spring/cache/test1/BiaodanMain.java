package com.spring.cache.test1;

public class BiaodanMain {
    private Biaodan biaodan;

    public BiaodanMain(Biaodan biaodan){
        this.biaodan = biaodan;
    }

    public void save(String title){
        biaodan.save(title);
    }

    public Biaodan getBiaodan() {
        return biaodan;
    }

    public void setBiaodan(Biaodan biaodan) {
        this.biaodan = biaodan;
    }
}
