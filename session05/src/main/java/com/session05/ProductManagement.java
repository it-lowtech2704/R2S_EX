package com.session05;

import com.session05.Product;

public class ProductManagement {
    private Product[] products;
    private int size;

    public ProductManagement() {
        this.products = new Product[10];
        this.size = 0;
    }

    public void addProduct(int id, String name, double price, int qty) {

        if (size >= products.length) {
            throw new IllegalStateException("Cannot add more than 10 products.");
        }

        if (exists(id)) {
            throw new IllegalArgumentException("Duplicate product ID.");
        }

        if (price < 0 || qty < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }

        Product p = new Product(id, name, price, qty);
        products[size++] = p;
    }

    public Product getProductById(int id) throws ProductNotFoundException {
        for (int i = 0; i < size; i++) {
            if (products[i].getId() == id) {
                return products[i];
            }
        }
        throw new ProductNotFoundException(id);
    }

    public void updateQuantity(int id, int newQty) throws ProductNotFoundException {
        if (newQty < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }
        else if (!exists(id)) {
            throw  new ProductNotFoundException(id);
        }

        Product p = getProductById(id);
        p.setQuantityInStock(newQty);
    }


    public void displayAllProducts() {
        if (size == 0) {
            System.out.println("(no products)");
            return;
        }
        for (int i = 0; i < size; i++) {
            products[i].displayProduct(products[i]);
        }
    }


    private boolean exists(int id) {
        for (int i = 0; i < size; i++) {
            if (products[i].getId() == id) {
                return true;
            }
        }
        return false;
    }
}
