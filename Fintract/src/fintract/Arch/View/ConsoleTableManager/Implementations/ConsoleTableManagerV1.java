/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Arch.View.ConsoleTableManager.Implementations;

import fintract.Arch.Model.RawData.TransactionCollection;
import fintract.Arch.Model.RawData.TransactionType;
import fintract.Arch.View.ConsoleTableManager.TableManager;
import fintract.Arch.View.ConsoleTableProperties.ConsoleTable;
import fintract.Arch.View.ConsoleTableProperties.Styles;
import fintract.Utility.ENUM_TYPES.ERROR_TYPE;
import fintract.Utility.ENUM_TYPES.TYPE;
import fintract.Utility.ENUM_TYPES.UTILITY;
import fintract.Utility.Colors.ConsoleColor;
import java.util.ArrayList;

/**
 *
 * @author hunter
 */
public class ConsoleTableManagerV1 implements TableManager{
    private ConsoleTable consoleTable;
    
    private void printTable(){
        System.out.print(consoleTable.withStyle(Styles.COMPACT));
    }
    
    private void printTable(ConsoleTable t){
        System.out.print(t.withStyle(Styles.COMPACT));
    }
    
    @Override
    public void printExpensesByMonth(ArrayList<TransactionType> exps) {
        consoleTable = new ConsoleTable(new String[]{"'EXP_ID","-EXPENSES","-AMOUNT","'TIME"});
        for(TransactionType exp: exps){
            consoleTable.addRow(exp.ID(),exp.Description(),exp.Amount(),exp.Time_recorded());
        }
        printTable();
    }

    @Override
    public void printAllExpenses(ArrayList<TransactionType> exps) {
        consoleTable = new ConsoleTable(new String[]{"'EXP_ID","-EXPENSES","-AMOUNT","'MONTH_DAY","'TIME"});
        for(TransactionType exp: exps){
            consoleTable.addRow(exp.ID(),exp.Description(),exp.Amount(),exp.MonthDay_Recorded(),exp.Time_recorded());
        }
        printTable();
    }

    @Override
    public void printIncomesByMonth(ArrayList<TransactionType> incs) {
        consoleTable = new ConsoleTable(new String[]{"'INC_ID","-INCOMES","-AMOUNT","'TIME"});
        for(TransactionType inc: incs){
            consoleTable.addRow(inc.ID(), inc.Description(), inc.Amount(), inc.Time_recorded());
        }
        printTable();
    }

    @Override
    public void printAllIncomes(ArrayList<TransactionType> incs) {
       consoleTable = new ConsoleTable(new String[]{"'INC_ID","-INCOMES","-AMOUNT","'MONTH_DAY","'TIME"});// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       for(TransactionType inc: incs){
            consoleTable.addRow(inc.ID(), inc.Description(), inc.Amount(), inc.MonthDay_Recorded(), inc.Time_recorded());
        }
        printTable();
    }

    @Override
    public void printAllTransactions(ArrayList<TransactionCollection> trans) {
        ConsoleTable incomesTable = new ConsoleTable(new String[]{"-MONTH_DAY","'TIME","-DESCRIPTIONS","-AMOUNT"});
        ConsoleTable expensesTable = new ConsoleTable(new String[]{"-MONTH_DAY","'TIME","-DESCRIPTIONS","-AMOUNT"});
        ArrayList<ArrayList<TransactionType>> incs_list = new ArrayList<>();
        ArrayList<ArrayList<TransactionType>> exps_list = new ArrayList<>();
        //List<String[]> sharedList = new ArrayList<>();
        for(TransactionCollection tran: trans){
            incs_list.add(tran.IncomeList());
            exps_list.add(tran.ExpenseList());
        }
        //PRINTS ALL THE INCOMES & EXPENSES IN TABLE FORMAT
        printAllTransaction(incs_list, incomesTable, TYPE.INCOME);
        printAllTransaction(exps_list, expensesTable, TYPE.EXPENSE);
    }
    
    private void printAllTransaction(ArrayList<ArrayList<TransactionType>> t, ConsoleTable table, TYPE transT){
        switch(transT){
            case INCOME -> {
                System.out.println();
                UTILITY.INCOMES.printColoredText();
                for(ArrayList<TransactionType> incList: t){
                    try{
                        ArrayList<TransactionType> incs = incList;
                        for(TransactionType inc : incs){
                            table.addRow(inc.MonthDay_Recorded(), inc.Time_recorded(), inc.Description(), inc.Amount());
                        }
                    }catch(NullPointerException npe){}
                }
            }
            case EXPENSE -> {
                UTILITY.EXPENSES.printColoredText(); 
                for(ArrayList<TransactionType> expList: t){
                    try{
                        ArrayList<TransactionType> exps = expList;
                        for(TransactionType exp : exps){
                            table.addRow(exp.MonthDay_Recorded(), exp.Time_recorded(), exp.Description(), exp.Amount());
                        }
                    }catch(NullPointerException npe){}
                }
            }
            default -> ERROR_TYPE.INVALID.printMessage();
        }
        printTable(table);
        printTotalInTableFormat(transT, t);
    }
    
    private <TRANSACTIONCOLLECTION> void printTotalInTableFormat(TYPE t, ArrayList<TRANSACTIONCOLLECTION> type){
        String amnt = "9,000,000";
        String coloredText="";
        switch(t){
            case INCOME -> coloredText = ConsoleColor.GREEN.getColor()+amnt+ConsoleColor.WHITE.getColor();
            case EXPENSE -> coloredText = ConsoleColor.RED.getColor()+amnt+ConsoleColor.WHITE.getColor();
            default -> ERROR_TYPE.INVALID.printMessage();
        }
        System.out.println("┌──────────────────────┬─────────────────────┐");
        System.out.println("│ TOTAL                │ "+coloredText+"           │");
        System.out.println("└──────────────────────┴─────────────────────┘");
        System.out.println();
    }
}
