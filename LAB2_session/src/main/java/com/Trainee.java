package com;

public class Trainee {
    private String id;
    private String name;
    private String gender;
    private byte age;

    public Trainee() {
    }

    public Trainee(String id, String name, String gender, byte age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Trainee ID: " + id);
        System.out.println("Trainee Name: " + name);
        System.out.println("Trainee Gender: " + gender);
        System.out.println("Trainee Age: " + age);
    }
}
