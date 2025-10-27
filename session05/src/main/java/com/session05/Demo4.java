package com.session05;

import java.util.Scanner;

public class Demo4 {
    public static int inputInterger() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int number = sc.nextInt();

        if (number < 0 || number > 100) throw new Exception();
        return number;
    }

    public static void main(String[] args) {
        boolean cont = true;
        int number = 0;
        do {
            try{
                number = inputInterger();
                cont = false;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer between 0 and 100.");
            }
        }while (cont);
        System.out.println("You entered: " + number);
    }
}
