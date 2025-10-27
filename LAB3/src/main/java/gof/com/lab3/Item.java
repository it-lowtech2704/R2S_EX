package gof.com.lab3;

import java.util.Scanner;

public class Item {
    protected String id;
    protected int value;
    protected String creator;

    protected Scanner sc = new Scanner(System.in);

    public Item() {
    }

    public Item(String id, int value, String creator) {
        this.id = id;
        this.value = value;
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void input() {
         while (true) {
            System.out.print("Enter ID: ");
            id = sc.nextLine();
            if (!id.trim().isEmpty()) break;
            else System.out.println("ID cannot be empty. Please try again.");
         }

        // Vòng lặp để đảm bảo nhập đúng số nguyên
        while(true) {
            try {
                System.out.print("Enter value: ");
                value = Integer.parseInt(sc.nextLine());
                if (value > 0) break; // Thoát nếu giá trị hợp lệ
                else System.out.println("Value must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }

        System.out.print("Enter creator: ");
        creator = sc.nextLine();
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Value: " + value + ", Creator: " + creator;
    }
}
