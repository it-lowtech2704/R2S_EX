package com.training.entities;


import com.training.utils.Validator;

import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String code;
    private String name;
    private boolean status;
    private short duration;
    private String flag;

    public Course() {
    }

    public Course(String code, String name, boolean status, short duration, String flag) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", duration=" + duration +
                ", flag='" + flag + '\'' +
                '}';
    }

    public void input(Scanner sc, ArrayList<Course> courses) {
        while (true) {
            System.out.println("Enter course code (format RA###): ");
            String c = sc.nextLine().trim();
            if (!Validator.validateCode(c)) {
                System.out.println("Invalid code format. Please try again.");
                continue;
            }
            if (Validator.isDuplicate(c, courses)) {
                System.out.println("Code already exists. Please enter a unique code.");
                continue;
            }
            this.code = c;
            break;
        }

        while (true) {
            System.out.println("Enter course name: ");
            String n = sc.nextLine().trim();
            if (n.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
                continue;
            }
            this.name = n;
            break;
        }

        while (true) {
            System.out.println("Enter status (true = active / false = inactive): ");
            String s = sc.nextLine().trim();
            if (!Validator.validateStatus(s)) {
                System.out.println("Invalid status. Please enter true or false.");
                continue;
            }
            this.status = Boolean.parseBoolean(s);
            break;

        }
        while (true) {
            System.out.println("Enter duration (>0): ");
            String d = sc.nextLine().trim();
            short du;
            try {
                du = Short.parseShort(d);
            } catch (NumberFormatException e) {
                System.out.println("Duration must be a number. Please try again.");
                continue;
            }
            if (!Validator.validateDuration(du)) {
                System.out.println("Duration must be greater than 0. Please try again.");
                continue;
            }
            this.duration = du;
            break;

        }
        while (true) {
            System.out.println("Enter flag (optional/ prerequisite/ N/A): ");
            String f = sc.nextLine().trim();
            if (!Validator.validateFlag(f)) {
                System.out.println("Invalid flag. Please enter 'optional', 'prerequisite', or 'N/A'.");
                continue;
            }
            this.flag = Validator.normalizeFlag(f);
            break;
        }

        System.out.printf("Course %s added successfully.%n", this.code);
    }
}
