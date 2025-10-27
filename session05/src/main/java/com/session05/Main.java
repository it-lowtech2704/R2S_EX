package com.session05;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }

    public static void main(String[] args) {
        ProductManagement manager = new ProductManagement();

        while (true) {
            System.out.println("==== Product Management Menu ====");
            System.out.println("1. Add Product");
            System.out.println("2. Retrieve Product by ID");
            System.out.println("3. Update Product Quantity");
            System.out.println("4. Exit");

            int option = readInt("Select an option: ");

            switch (option) {
                case 1 -> {
                    System.out.println("Enter product details:");
                    int id = readInt("Product ID: ");
                    String name = readNonEmpty("Product Name: ");
                    double price = readDouble("Product Price: ");
                    int qty = readInt("Quantity in Stock: ");

                    try {
                        manager.addProduct(id, name, price, qty);
                        System.out.println("Product added successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 2 -> {

                    int id = readInt("Enter product ID to retrieve: ");
                    try {
                        Product p = manager.getProductById(id);
                        p.displayProduct(p);
                    } catch (ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {

                    int id;
                    while (true) {
                        id = readInt("Enter product ID to update: ");
                        try {
                            Product p = manager.getProductById(id);
                            break;
                        } catch (ProductNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    int newQty = readInt("Enter new quantity: ");
                    try {
                        manager.updateQuantity(id, newQty);
                        System.out.println("Quantity updated successfully.");
                    } catch (ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> {
                    System.out.println("Invalid choice.");
                }
            }

            System.out.println();
        }
    }
}

