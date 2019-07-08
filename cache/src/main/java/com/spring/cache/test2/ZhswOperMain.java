package com.spring.cache.test2;

public class ZhswOperMain {

    private ZhswOper zhswOper;

    public ZhswOperMain(ZhswOper zhswOper) {
        this.zhswOper = zhswOper;
    }

    public void operation(ZhswBiaodan zhswBiaodan){
        zhswOper.operation(zhswBiaodan);
    }

    public ZhswOper getZhswOper() {
        return zhswOper;
    }

    public void setZhswOper(ZhswOper zhswOper) {
        this.zhswOper = zhswOper;
    }
}
