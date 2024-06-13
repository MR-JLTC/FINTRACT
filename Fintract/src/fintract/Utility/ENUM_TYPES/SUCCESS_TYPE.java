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
public enum SUCCESS_TYPE {
   SUCCESSFUL_INSERTION("SUCCESSFULLY ADDED "),
   SUCESSFUL_DELETION("SUCESSFULLY DELETED "),
   SUCESSFUL_UPDATE("SUCCESSFULLY UPDATED "),
   SUCESSFUL_FETCH("SUCCESSFULLY FETCH "),
   SUCCESFUL_DOCSGCREATION("PRINTABLE DOCS SUCCESSFULLY CREATED");
   
   private final String message;
   
   SUCCESS_TYPE(String m){
     this.message = m;
   }
   
   //Using compile-time polymorphism [overloading]
   public void printMessage(){
       System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+ConsoleColor.BLUE.getColor()+message+ConsoleColor.WHITE.getColor());
   }
   
   public void printMessage(String p){
       System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+ConsoleColor.BLUE.getColor()+message+p+ConsoleColor.WHITE.getColor());
   }
   
    public static void printCustomErrorMessage(String m){
        System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+ConsoleColor.BLUE.getColor()+m+ConsoleColor.WHITE.getColor());
    }
}
