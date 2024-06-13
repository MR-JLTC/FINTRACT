/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fintract.Arch.View.ConsoleTableManager;

import fintract.Arch.Model.RawData.TransactionCollection;
import fintract.Arch.Model.RawData.TransactionType;
import java.util.ArrayList;

/**
 *
 * @author hunter
 */
public interface TableManager {
    /** Manage printing for recorded expenses
     * @param exps **/
    void printExpensesByMonth(ArrayList<TransactionType> exps);
    void printAllExpenses(ArrayList<TransactionType> exps);
    
    /** Manage printing for recorded incomes
     * @param incs**/
    void printIncomesByMonth(ArrayList<TransactionType> incs);
    void printAllIncomes(ArrayList<TransactionType> incs);
    
    /** Manage printing for all recorded Transactions made by ye
     * @param transCltn**/
    void printAllTransactions(ArrayList<TransactionCollection> transCltn);
}
