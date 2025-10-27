package com.session_4;

import java.util.Scanner;

public class Program {
    private Product[] products = new Product[100];
    private Byte numberOfProducts = 0;

    public Program() {
    }

    public void addProduct(Product product) {
        products[numberOfProducts++] = product;
    }

    public void showProducts() {
        if (numberOfProducts == 0) {
            System.out.println("No products to display.");
            return;
        }
        for (int i = 0; i < numberOfProducts; i++) {
            System.out.println(products[i]);
        }
    }

    public Product findProduct(int id) {
        for (int i = 0; i < numberOfProducts; i++) {
            if (products[i].getId() == id) return products[i];
        }
        return null;
    }

    public Product deleteProduct(int id) {
        for (int i = 0; i < numberOfProducts; i++) {
            if (products[i].getId() == id) {
                Product deletedProduct = products[i];
                for (int j = i; j < numberOfProducts - 1; j++) {
                    products[j] = products[j + 1];
                }
                products[--numberOfProducts] = null;
                return deletedProduct;
            }
        }
        return null;
    }


    private static final Scanner sc = new Scanner(System.in);

    private static String readNonEmpty(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int readInt(String prompt) {
       while (true) {
           System.out.println(prompt);
           String s = sc.nextLine();
           try {
               return Integer.parseInt(s);
           } catch (NumberFormatException e) {
               System.out.println("Invalid integer. Please try again.");
           }
       }
    }

    private static float readFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                return Float.parseFloat(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid float. Please try again.");
            }
        }
    }


    private static Product readProduct(String prompt) {
        int type;
        while (true){
            System.out.println("Choose product type: 1) Electronics  2) Clothing");
            type = readInt("Type: ");
            if (type == 1 || type == 2) break;
            System.out.println("Invalid type. Please enter 1 or 2.");
         }
        int id = readInt("Id: ");
        String name = readNonEmpty("Name: ");
        float price = readFloat("Price: ");
        if (type == 1) {
            String brand = readNonEmpty("Brand: ");
            return new Electronics(id, name, price, brand);
        } else if (type == 2) {
            String size = readNonEmpty("Size: ");
            return new Clothing(id, name, price, size);
        } else {
            System.out.println("Invalid type → default Electronics(brand='Unknown').");
            return new Electronics(id, name, price, "Unknown");
        }
    }

    public static void main(String[] args) {
        Program app = new Program();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add product");
            System.out.println("2. Display all");
            System.out.println("3. Find by id");
            System.out.println("4. Delete by id");
            System.out.println("0. Exit");
            int choice = readInt("Your choice: ");

            switch (choice) {

                case 1 -> app.addProduct(readProduct("Add Product"));
                case 2 -> app.showProducts();
                case 3 -> {
                    if(app.numberOfProducts == 0){
                        System.out.println("No products to find.");
                        break;
                    }
                    int id = readInt("Enter id to find: ");
                    Product p = app.findProduct(id);
                    System.out.println(p == null ? "Not found." : p);
                }
                case 4 -> {
                    if (app.numberOfProducts == 0) {
                        System.out.println("No products to delete.");
                        break;
                    }
                    int id = readInt("Enter id to delete: ");
                    Product p = app.deleteProduct(id);
                    System.out.println(p == null ? "Not found." : "Deleted: " + p);
                }
                case 0 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
