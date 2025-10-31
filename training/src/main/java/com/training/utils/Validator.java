package com.training.utils;

import com.training.entities.Course;

import java.util.ArrayList;

public class Validator {
    public static boolean validateCode(String code){
        if ( code == null)
            return false;
        return code.matches("RA\\d{3}$");
    }

    public static boolean isDuplicate(String code, ArrayList<Course> courses ) {;
     for ( Course c : courses ) {
         if (c.getCode().equalsIgnoreCase(code)){
                return true;
         }
     }
        return false;
    }

    public static boolean validateStatus(String status){
        if ( status == null)
            return false;
        return status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false");
    }

    public static boolean validateDuration(Short duration){
        return duration > 0;
    }

    public static boolean validateFlag(String flag){
        if(flag == null)
           return false;
        String f = flag.trim().toLowerCase();
        return f.equals("Optional") ||
                f.equals("prerequisite") ||
                f.equals("n/a")||
                f.equals("na")||
                f.equals("n|a");
    }

    public static String normalizeFlag(String flag){
        String f = flag.trim().toLowerCase();
        if (f.equals("optional"))
            return "Optional";
        else if (f.equals("prerequisite"))
            return "Prerequisite";
        else
            return "N/A";
    }
}
