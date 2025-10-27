package gof.com.lab3;

import java.util.Scanner;

public class ShopManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ItemList inventory = new ItemList();
        int choice;

        do {
            System.out.println("\n--- ANTIQUE SHOP MANAGEMENT ---");
            System.out.println("1. Add a new Vase");
            System.out.println("2. Add a new Statue");
            System.out.println("3. Add a new Painting");
            System.out.println("4. Display all items");
            System.out.println("5. Find item by creator");
            System.out.println("6. Display items by type (Vase/Statue/Painting)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter a number.");
                choice = -1; // Đặt lại lựa chọn để lặp lại
                continue;
            }

            switch (choice) {
                case 1:
                    Item vase = new Vase();
                    vase.input(); // Đa hình: gọi input() của Vase
                    if (inventory.addItem(vase)) {
                        System.out.println("Vase added successfully!");
                    } else {
                        System.out.println("Failed to add Vase (Inventory full or ID duplicated).");
                    }
                    break;
                case 2:
                    Item statue = new Statue();
                    statue.input(); // Đa hình: gọi input() của Statue
                    if (inventory.addItem(statue)) {
                        System.out.println("Statue added successfully!");
                    } else {
                        System.out.println("Failed to add Statue (Inventory full or ID duplicated).");
                    }
                    break;
                case 3:
                    Item painting = new Painting();
                    painting.input(); // Đa hình: gọi input() của Painting
                    if (inventory.addItem(painting)) {
                        System.out.println("Painting added successfully!");
                    } else {
                        System.out.println("Failed to add Painting (Inventory full or ID duplicated).");
                    }
                    break;
                case 4:
                    inventory.displayAll();
                    break;
                case 5:
                    if (inventory.isEmpty()) {
                        System.out.println("Inventory is empty. No items to find.");
                        break;
                    }
                    System.out.print("Enter creator name to find: ");
                    String creator = sc.nextLine();
                    Item foundItem = inventory.findItem(creator);
                    if (foundItem != null) {
                        System.out.println("Item found:");
                        System.out.println(foundItem.toString());
                    } else {
                        System.out.println("No item found by this creator.");
                    }
                    break;
                case 6:
                    if (inventory.isEmpty()) {
                        System.out.println("Inventory is empty. No items to display.");
                        break;
                    }
                    System.out.print("Enter type to display (Vase/Statue/Painting): ");
                    String type = sc.nextLine();
                    inventory.displayItemsByType(type);
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        sc.close();
    }
}
