package com.manage.demo.pojo;

public class Orders {
    private String oid;
    private String uid;
    private String gid_size_qua;
    private String order_time;
    private String isPay;
    private String isdelivery;
    private String expressName;
    private String express;
    private String discount;
    private String price;
    private String actual;
    private String coupon_id;
    private String check;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getIsdelivery() {
        return isdelivery;
    }

    public void setIsdelivery(String isdelivery) {
        this.isdelivery = isdelivery;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getGid_size_qua() {
        return gid_size_qua;
    }

    public void setGid_size_qua(String gid_size_qua) {
        this.gid_size_qua = gid_size_qua;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "{" +
                "oid:'" + oid + '\'' +
                ", uid='" + uid + '\'' +
                ", gidSizeQua='" + gid_size_qua + '\'' +
                ", orderTime='" + order_time + '\'' +
                ", isPay='" + isPay + '\'' +
                ", isdelivery='" + isdelivery + '\'' +
                ", expressName='" + expressName + '\'' +
                ", express='" + express + '\'' +
                ", discount='" + discount + '\'' +
                ", price='" + price + '\'' +
                ", actual='" + actual + '\'' +
                ", couponId='" + coupon_id + '\'' +
                ", check='" + check + '\'' +
                '}';
    }
}
