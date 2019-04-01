package com.example.small_project.bean;

public class BondBean {
    int count;
    int commodityId;

    public BondBean() {

    }
    public BondBean(int count, int commodityId) {
        this.count = count;
        this.commodityId = commodityId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public int getCommodityId() {
        return commodityId;
    }
}
