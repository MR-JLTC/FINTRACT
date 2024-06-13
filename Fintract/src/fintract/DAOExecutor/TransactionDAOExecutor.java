/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.DAOExecutor;

import fintract.Arch.Model.RawData.TransactionType;
import fintract.Utility.ENUM_TYPES.ERROR_TYPE;
import fintract.Utility.ENUM_TYPES.TYPE;
import static fintract.Utility.ENUM_TYPES.TYPE.EXPENSE;
import static fintract.Utility.ENUM_TYPES.TYPE.INCOME;
import java.util.ArrayList;
import fintract.Processors.DAOProcessor;
import fintract.Utility.DateHandler;
import static fintract.Utility.ENUM_TYPES.TYPE.ALL_INCOMES;
import static fintract.Utility.ENUM_TYPES.TYPE.TRANSACTIONLIST;

/**
 *
 * @author hunter
 */ 
public class TransactionDAOExecutor{
    private final DAOProcessor process;
    
    public TransactionDAOExecutor(DAOProcessor process){
        this.process = process;
    }
    
    public String insertAccountData(String name){
        return process.insertAcntData(name);
    }
    
    public String getSearchedName(String n){
        return process.getSearchedName(n);
    }
    
    public void insertData(String acntName, TransactionType transT, TYPE transactionType) {
        switch(transactionType){
            case INCOME -> process.insertIncomeData(transT, acntName);
            case EXPENSE -> process.insertExpenseData(transT, acntName);
            default -> ERROR_TYPE.INVALID.printMessage();
        }
    }

    public void updateData(int id, TransactionType transT, TYPE type) {
        switch(type){
            case INCOME -> process.updateIncomeData(id, transT);
            case EXPENSE -> process.updateExpenseData(id, transT);
            default -> ERROR_TYPE.INVALID.printMessage();
        }
    }

    public void deleteData(int id, TYPE transactionType) {
        switch(transactionType){
            case INCOME -> process.deleteIncomeData(id);
            case EXPENSE -> process.deleteExpenseData(id);
            default -> ERROR_TYPE.INVALID.printMessage();
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> getTransactions(TYPE transactionType, String date, String acntName) {
        ArrayList<T> type = new ArrayList<>();
        switch (transactionType) {
            case INCOME -> 
                type = (ArrayList<T>) process.getIncomeData(date, acntName);
            case EXPENSE -> 
                type = (ArrayList<T>) process.getExpenseData(date, acntName);
            case ALL_INCOMES -> 
                type = (ArrayList<T>) process.getListOfIncomes(acntName);
            case ALL_EXPENSES -> 
                type = (ArrayList<T>) process.getListOfExpenses(acntName);
            case TRANSACTIONLIST -> 
                type = (ArrayList<T>) process.getTransactionData(acntName);
            default -> ERROR_TYPE.INVALID.printMessage();
        }
        return type;
    }
}
