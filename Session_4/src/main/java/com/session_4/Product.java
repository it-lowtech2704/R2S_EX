package com.session_4;

public abstract class Product {
    protected int id;
    protected String name;
    protected float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract String details();

    @Override
    public String toString() {
        return String.format("id=%d | name=%s | price=%.2f | %s",
                id, name, price, details());
    }

}
