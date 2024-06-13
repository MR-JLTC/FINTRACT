/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Arch.View.ViewManager;


import fintract.Arch.Model.RawData.TransactionCollection;
import fintract.Arch.Model.RawData.TransactionType;
import fintract.Arch.View.ConsoleTableManager.TableManager;
import fintract.Arch.View.HeaderLogo.ASCII_HEADERLOGO;
import fintract.Utility.Colors.ConsoleColor;
import fintract.Utility.ENUM_TYPES.ERROR_TYPE;
import fintract.Utility.ENUM_TYPES.SUCCESS_TYPE;
import fintract.Utility.ENUM_TYPES.VIEW_TYPE;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hunter
 */
public class ViewManager{
    private final TableManager table_manager;
;    private final Scanner input;
    
    public ViewManager(TableManager manager){
        input = new Scanner(System.in);
        this.table_manager = manager;
    }
    
    public void printHeaderLogo(){
        ASCII_HEADERLOGO.printLogo(0);
    }
    
    public void outputSuccessMessage(SUCCESS_TYPE t){
        t.printMessage();
    }
    
    public void outputSuccessMessage(SUCCESS_TYPE t, String m){
        t.printMessage(m);
    }
    
    public void outputSuccessMessage(String m){
        SUCCESS_TYPE.printCustomErrorMessage(m);
    }
    
    public void outputErrorMessage(ERROR_TYPE t){
        t.printMessage();
    }
    
    public void outputErrorMessage(ERROR_TYPE t, String m){
        t.printMessage(m);
    }
    
    public void outputErrorMessage(String customMessage){
        ERROR_TYPE.printCustomErrorMessage(customMessage);
    }
    
    public void outputNotifMessage(String m){
        System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+m);
    }
    
    public void outputInfoMessage(String info){
        System.out.println(ConsoleColor.GRAY.getColor()+"[*]> "+ConsoleColor.BLUE.getColor()+info+ConsoleColor.WHITE.getColor());
    }
    
    //VIEWING TYPES
    public String getInputFromMain(){
        System.out.print(ConsoleColor.WHITE.getColor()+"[main]> ");
        return input.nextLine();
    }
    
    public String getInputFromAccount(String acntName){
        System.out.print(ConsoleColor.WHITE.getColor()+"["+acntName+"]> ");
        return input.nextLine();
    }
    
    @SuppressWarnings("unchecked")
    public <ListType> void displayDataInTableFormat(VIEW_TYPE t, ArrayList<ListType> lt){
        switch(t){
            case OUTPUT_EXPENSES_BYMONTH_VIEWER -> table_manager.printExpensesByMonth((ArrayList<TransactionType>) lt);
            case OUTPUT_INCOMES_BYMONTH_VIEWER -> table_manager.printIncomesByMonth((ArrayList<TransactionType>) lt);
            case OUTPUT_EXPENSES_BYCURRENTYEAR_VIEWER -> table_manager.printAllExpenses((ArrayList<TransactionType>) lt);
            case OUTPUT_INCOMES_BYCURRENTYEAR_VIEWER -> table_manager.printAllIncomes((ArrayList<TransactionType>) lt);
            case ALL_EXPS_INCS_VIEWER -> table_manager.printAllTransactions((ArrayList<TransactionCollection>) lt);
            default -> ERROR_TYPE.INVALID.printMessage();
        }
    } 
}
