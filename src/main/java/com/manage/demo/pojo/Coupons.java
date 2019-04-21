package com.manage.demo.pojo;

public class Coupons {
    private String did;
    private String name;
    private String percent;
    private String quanlity;
    private String dead_time;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(String quanlity) {
        this.quanlity = quanlity;
    }

    public String getDead_time() {
        return dead_time;
    }

    public void setDead_time(String dead_time) {
        this.dead_time = dead_time;
    }

    @Override
    public String toString() {
        return "Coupons{" +
                "did='" + did + '\'' +
                ", name='" + name + '\'' +
                ", percent='" + percent + '\'' +
                ", quanlity='" + quanlity + '\'' +
                ", dead_time='" + dead_time + '\'' +
                '}';
    }
}
