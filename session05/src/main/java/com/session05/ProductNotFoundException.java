package com.session05;

public class ProductNotFoundException extends  Exception {
    public ProductNotFoundException(int MissingID) {

        super("Product with ID " + MissingID + " not found.");

    }

    public ProductNotFoundException(String message) {
        super(message);
    }

}
