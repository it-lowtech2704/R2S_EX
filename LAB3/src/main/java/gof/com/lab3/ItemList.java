package gof.com.lab3;

import java.util.List;

public class ItemList {
    private Item[] list;
    private int numOfItem;
    private static final int MAX = 100;

    public ItemList() {
        list = new Item[MAX];
        numOfItem = 0;
    }

    public boolean addItem(Item item) {
        if (item == null || numOfItem >= MAX) {
            return false; // Danh sách đầy hoặc item rỗng
        }

        // Kiểm tra trùng lặp ID (theo yêu cầu thực tế, không có trong UML)
        for (int i = 0; i < numOfItem; i++) {
            if (list[i].getId().equalsIgnoreCase(item.getId())) {
                System.out.println("Error: Item ID already exists.");
                return false;
            }
        }

        list[numOfItem] = item;
        numOfItem++;
        return true;
    }

    public void displayAll() {
        if (numOfItem == 0) {
            System.out.println("The inventory is empty.");
            return;
        }

        System.out.println("--- All Items in Inventory ---");
        for (int i = 0; i < numOfItem; i++) {
            System.out.println((i + 1) + ". " + list[i].toString());
        }
        System.out.println("--------------------------------");
    }
    public Item findItem(String creator) {
        for (int i = 0; i < numOfItem; i++) {
            if (list[i].getCreator().equalsIgnoreCase(creator)) {
                return list[i]; // Trả về vật phẩm đầu tiên tìm thấy
            }
        }
        return null; // Không tìm thấy
    }

    public void displayItemsByType(String type) {
        if (numOfItem == 0) {
            System.out.println("The inventory is empty.");
            return;
        }

        int count = 0;
        System.out.println("--- Items of type: " + type + " ---");

        for (int i = 0; i < numOfItem; i++) {
            if (type.equalsIgnoreCase("Vase") && list[i] instanceof Vase) {
                System.out.println(list[i].toString());
                count++;
            } else if (type.equalsIgnoreCase("Statue") && list[i] instanceof Statue) {
                System.out.println(list[i].toString());
                count++;
            } else if (type.equalsIgnoreCase("Painting") && list[i] instanceof Painting) {
                System.out.println(list[i].toString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No items found for this type.");
        }
        System.out.println("---------------------------------");
    }

    public boolean isEmpty() {
        return numOfItem == 0;
    }
}

