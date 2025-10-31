package com.training.main;

import com.training.entities.Course;
import com.training.utils.Validator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class CourseManagement {
    private ArrayList<Course> courses;

    public CourseManagement() {
        courses = new ArrayList<>();
    }

    public void createCources(Scanner sc){
        Course c = new Course();
        c.input(sc, courses);
        courses.add(c);
        System.out.println("Course added successfully!");
    }

    public ArrayList<Course> searchCourses(String type,String data){
        ArrayList<Course> result = new ArrayList<>();
        String t = type.trim().toLowerCase(Locale.ROOT);
        String d = data.trim().toLowerCase(Locale.ROOT);

        for ( Course c : courses ) {
            switch (t){
                case "code" -> {
                    if (c.getCode().equalsIgnoreCase(data))
                        result.add(c);
                }
                case "name" -> {
                    if (c.getName().toLowerCase(Locale.ROOT).contains(d))
                        result.add(c);
                }
                case "status" -> {
                    String str = c.isStatus() ? "true" : "false";
                    if (str.equalsIgnoreCase(d))
                        result.add(c);
                }
                case "flag" -> {
                    String no = Validator.normalizeFlag(data);
                    if(c.getFlag().equalsIgnoreCase(no)){
                        result.add(c);
                    }
                }
                default -> {
                    System.out.println("Invalid search type!");
                }
            }
        }
        return result;
    }

    public void displayCoursesByFlag(Scanner sc){
        System.out.println("Enter flag to filter (Optional, Prerequisite, N/A): ");
        String f = sc.nextLine().trim();
        String norm = Validator.normalizeFlag(f);

        boolean found = false;
        for ( Course c : courses ) {
            if (c.getFlag().equalsIgnoreCase(norm)){
                System.out.println(c);
                found = true;
            }
        }
        if (!found){
            System.out.println("No courses found with the specified flag.");
        }
    }

    public void printALL(){
        if(courses.isEmpty()){
            System.out.println("No courses available.");
            return;
        }
        for ( Course c : courses ) {
            System.out.println(c);
        }
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("==========Course Management Menu======");
            System.out.println("1. Add new course");
            System.out.println("2. Search courses by attribute");
            System.out.println("3. Display courses by flag");
            System.out.println("4. Display all courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    createCources(sc);
                }

                case "2" -> {
                    System.out.println("Search by (code, name, status, flag): ");
                    System.out.println("Type: ");
                    String type = sc.nextLine().trim();

                    System.out.println("Search Value");
                    String data = sc.nextLine().trim();
                    ArrayList<Course> results = searchCourses(type, data);
                    if (results.isEmpty()) {
                        System.out.println("No match courses");
                    } else {
                        System.out.println("=== Results ===");
                        for (Course c : results) {
                            System.out.println(c);
                        }
                    }
                    System.out.println("================");
                }

                case "3" -> {
                    displayCoursesByFlag(sc);
                    System.out.println("================");
                }

                case "4" -> {
                    System.out.println("Display all courses");
                    printALL();
                    System.out.println("================");
                }

                case "5" -> {
                    System.out.println("Exiting the program. Goodbye!");
                    sc.close();
                    return;
                }


                default -> {
                    System.out.println("Invalid choice. Please select a valid option (1-5).");
                }
            }
        }
    }

    public static void main(String[] args) {
        CourseManagement cm = new CourseManagement();
        cm.showMenu();
    }

}
