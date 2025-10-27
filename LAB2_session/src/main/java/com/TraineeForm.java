package com;

import java.util.Scanner;

public class TraineeForm {
    private Scanner scanner;

    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getId() {
        while (true) {
            System.out.print("Enter Trainee ID: ");
            String id = scanner.nextLine().trim();
            if (!id.isEmpty()) {
                return id;
            } else {
                System.out.println("ID cannot be empty");
            }
        }
    }

    public Trainee getTrainee(){
        String name = readNonEmpty("Enter Trainee Name: ");
        String gender = readGender("Enter Trainee Gender (male/female): ");
        byte age = readAge("Enter Trainee Age (6-100): ");

        Trainee t = new Trainee();
        t.setName(name);
        t.setGender(gender);
        t.setAge(age);
        return t;
    }


    // function to helper
    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private String readGender(String prompt) {
        while (true) {
            System.out.print(prompt);
            String ge = scanner.nextLine().trim();
            if (ge.equalsIgnoreCase("male")||ge.equalsIgnoreCase("female")) {
            return ge.toLowerCase();
            }
            System.out.println("Gender must be 'male' or 'female'. Please try again.");
        }
    }

    private byte readAge(String prompt) {
        while (true) {
            System.out.print(prompt);
                String s = scanner.nextLine().trim();
                try{
                    int n = Integer.parseInt(s);
                    if (n > 6 && n < 100) {
                        return (byte) n;
                    } else {
                        System.out.println("Age must be between 6 and 100. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age format. Please enter a valid number.");
                }
        }
    }
}
