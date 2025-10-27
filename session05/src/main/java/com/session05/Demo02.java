package com.session05;

public class Demo02 {
    public static void main (String[] args) {
        int number[] = {10, 20, 30, 40, 50};
        try {
            for (int i = 0; i <= number.length; i++) {
                System.out.println("Element at index " + i + ": " + number[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Array index is out of bounds!");
        }
    }
}
