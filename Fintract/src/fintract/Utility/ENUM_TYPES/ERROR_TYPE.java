/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package fintract.Utility.ENUM_TYPES;

import fintract.Utility.Colors.ConsoleColor;

/**
 *
 * @author hunter
 */
public enum ERROR_TYPE {
    INSERTIOIN_FAILURE("FAILED TO ADD "),
    UPDATE_FAILURE("FAILED TO UPDATE "),
    DELETION_FAILURE("FAILED TO DELETE "),
    FETCHING_FAILURE("FAILED TO FETCH THE DATA OF "),
    DOCSCREATION_FAILURE("FAILED TO CREATE DOCS FILE"),
    CONNECTION_FAILED("DATABASE CONNECTION FAILURE"),
    INVALID("INVALID TYPE");
    
    private final String m;
    
    ERROR_TYPE(String m){
        this.m = m;
    }
    
    //Using compile-time polymorphism [overloading]
    public void printMessage(){
        System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+ConsoleColor.RED.getColor()+m+ConsoleColor.WHITE.getColor());
    }
    
    public void printMessage(String c){
        System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+ConsoleColor.RED.getColor()+m+c+ConsoleColor.WHITE.getColor());
    }
    
    public static void printCustomErrorMessage(String m){
        System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+ConsoleColor.RED.getColor()+m+ConsoleColor.WHITE.getColor());
    }
}
