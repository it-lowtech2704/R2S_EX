package gof.com.lab3;

import java.util.Scanner;

public class Statue extends Item{
    private int weight;
    private String color;

    public Statue() {
        super();
    }

    public Statue(String id, int value, String creator, int weight, String color) {
        super(id, value, creator);
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void input() {
        super.input();
        // Nhập thông tin riêng của Statue
        while(true) {
            try {
                System.out.print("Enter weight: ");
                weight = Integer.parseInt(sc.nextLine());
                if (weight > 0) break;
                else System.out.println("Weight must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    @Override
    public String toString() {
        return "[Statue] " + super.toString() + ", Weight: " + weight + ", Color: " + color;
    }
}
