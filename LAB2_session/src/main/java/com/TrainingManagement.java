package com;

import java.util.Scanner;

public class TrainingManagement {
    private TraineeForm traineeForm;
    private Scanner scanner;
    private Trainee[] listOfTrainees = new Trainee[100];
    private byte count = 0;

    public TrainingManagement() {
        scanner = new Scanner(System.in);
        traineeForm = new TraineeForm(scanner);
    }

    public static void main(String[] args) {
        TrainingManagement app = new TrainingManagement();
        app.run();
    }

    private void run() {
        int choice = -1;
        String line;

        do {
            System.out.println("===== Training Management =====");
            System.out.println("1. Create trainee");
            System.out.println("2. Display all trainees");
            System.out.println("3. Find trainee by ID");
            System.out.println("4. Find trainee by Name");
            System.out.println("5. Update trainee by ID");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");

            line = scanner.nextLine();
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }
            if (choice < 1 || choice > 6) {
                System.out.println("Choice must be between 1 and 6. Please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    addTrainee();
                    break;
                case 2:
                    displayAllTrainees();
                    break;
                case 3:
                    System.out.print("Enter Trainee ID to search: ");
                    String id = scanner.nextLine();
                    Trainee tById = findTraineeById(id);
                    if (tById != null) {
                        tById.displayInfo();
                    } else {
                        System.out.println("Trainee with ID " + id + " not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Trainee Name to search: ");
                    String name = scanner.nextLine();
                    Trainee tByName = findTraineeByName(name);
                    if (tByName != null) {
                        tByName.displayInfo();
                    } else {
                        System.out.println("Trainee with Name " + name + " not found.");
                    }
                    break;
                case 5:
                    updateFlow();
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
            }
        }  while (choice != 6) ;
    }



    public void addTrainee(){
        if(count >= listOfTrainees.length){
            System.out.println("Trainee list is full. Cannot add more trainees.");
            return;
        }
        String id;
        while (true) {
            id = traineeForm.getId();
            if (findTraineeById(id) != null) {
                System.out.println("Trainee ID already exists. Please enter a unique ID.");
            } else
                break;
        }
        Trainee info = traineeForm.getTrainee();
        Trainee newTrainee = new Trainee(id, info.getName(), info.getGender(), info.getAge());
        listOfTrainees[count++] = newTrainee;
        System.out.println("Trainee added successfully.");
    }

    public void displayAllTrainees() {
        if (count == 0) {
            System.out.println("No trainees to display.");
            return;
        }
        for (int i = 0; i < count; i++) {
            listOfTrainees[i].displayInfo();
            System.out.println("-----------------------");
        }
    }

    public Trainee findTraineeById(String id) {
        for (int i = 0; i < count; i++) {
            if (listOfTrainees[i].getId().equals(id)) {
                return listOfTrainees[i];
            }
        }
        return null;
    }

    public Trainee findTraineeByName(String name) {
        for (int i = 0; i < count; i++) {
            if (listOfTrainees[i].getName().equalsIgnoreCase(name)) {
                return listOfTrainees[i];
            }
        }
        return null;
    }

    public void updateTraineeById(String id) {
        Trainee t = findTraineeById(id);
        if (t == null) {
            System.out.println("Trainee with ID " + id + " not found.");
            return;
        }
        System.out.println("Updating information for Trainee ID: " + id);
        Trainee updatedInfo = traineeForm.getTrainee();
        t.setName(updatedInfo.getName());
        t.setGender(updatedInfo.getGender());
        t.setAge(updatedInfo.getAge());
        System.out.println("Trainee information updated successfully.");
    }

    private void updateFlow() {
        System.out.println("Enter Trainee ID to update: ");
        String id = scanner.nextLine();
        Trainee t = findTraineeById(id);
        if (t == null) {
            System.out.println("Trainee with ID " + id + " not found.");
            return;
        }

        System.out.println("Leave empty to keep current value.");

        System.out.print("Enter new Name (current: " + t.getName() + "): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            t.setName(name);
        }
        while (true) {
            System.out.print("Enter new Gender (male/female) (current: " + t.getGender() + "): ");
            String g = scanner.nextLine().trim();
            if (g.isEmpty()) {
                break;
            } else if (g.equalsIgnoreCase("male") || g.equalsIgnoreCase("female")) {
                t.setGender(g.toLowerCase());
                break;
            } else {
                System.out.println("Gender must be 'male' or 'female'. Please try again.");
            }
        }
        while (true) {
            System.out.print("Enter new Age (6-100) (current: " + t.getAge() + "): ");
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) {
                break;
            }
            try {
                int n = Integer.parseInt(s);
                if (n > 6 && n < 100) {
                    t.setAge((byte) n);
                    break;
                } else {
                    System.out.println("Age must be between 6 and 100. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid age.");
            }
        }
        System.out.println("Trainee information updated successfully.");
    }
}
