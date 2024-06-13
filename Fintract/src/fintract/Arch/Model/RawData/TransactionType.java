/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package fintract.Arch.Model.RawData;

/**
 *
 * @author hunter
 */
public record TransactionType(int ID, 
        String Description, 
        double Amount, 
        String MonthDay_Recorded, 
        String Time_recorded,
        String Year_Recorded) {
    public TransactionType(String description, 
        double amount, 
        String MonthDay_Recorded, 
        String time_recorded,
        String Year_Recorded){
        this(0,description, amount, MonthDay_Recorded,  time_recorded, Year_Recorded);
    }
}
