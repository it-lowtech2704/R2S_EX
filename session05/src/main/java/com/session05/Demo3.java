package com.session05;

import java.io.File;
import java.io.PrintWriter;

public class Demo3 {
    public static void main (String[] args) {
        try{
       File file = new File("example.txt");
       PrintWriter out = new PrintWriter(file);
    }
    catch (Exception e) {
        e.printStackTrace();
        System.out.println("An error occurred while creating the file.");
    }
    }
}
