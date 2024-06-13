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
public enum UTILITY {
    INCOMES(ConsoleColor.BLUE.getColor()+"INCOMES"+ConsoleColor.WHITE.getColor()),
    EXPENSES(ConsoleColor.BLUE.getColor()+"EXPENSES"+ConsoleColor.WHITE.getColor());
    
    private final String s;
    UTILITY(String s){
        this.s = s;
    }
      
    public void printColoredText(){
        System.out.println(s);
    }
}
