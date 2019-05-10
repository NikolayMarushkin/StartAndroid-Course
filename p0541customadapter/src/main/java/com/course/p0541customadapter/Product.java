package com.course.p0541customadapter;

// Product.java – класс, описывающий товар:
public class Product {
    String  name;
    int     price;
    int     image;
    boolean box;

    Product(String _describe, int _price, int _image, boolean _box) {
        name = _describe;
        price = _price;
        image = _image;
        box = _box;
    }
}
