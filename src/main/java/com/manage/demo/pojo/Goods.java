package com.manage.demo.pojo;


public class Goods {
    private String gid;
    private String one;
    private String two;
    private String three;
    private String brand;
    private String title;
    private String description;
    private String discount;
    private String price;
    private String pro_code;
    private String color;
    private String size;
    private String im;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPro_code() {
        return pro_code;
    }

    public void setPro_code(String pro_code) {
        this.pro_code = pro_code;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    @Override
    public String toString() {
        return "Good{" +
                "gid='" + gid + '\'' +
                "one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                ", brand='" + brand + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", discount='" + discount + '\'' +
                ", price='" + price + '\'' +
                ", pro_code='" + pro_code + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", im='" + im + '\'' +
                '}';
    }
}
