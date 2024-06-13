/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Processors;

import fintract.Arch.Model.RawData.TransactionCollection;
import fintract.Arch.Model.RawData.TransactionType;
import fintract.Utility.ENUM_TYPES.QUERY_RESULT;
import java.util.ArrayList;
/**
 *
 * @author hunter
 */
public interface DAOProcessor {
     public String insertAcntData(String n);
     public String getSearchedName(String n);
     public QUERY_RESULT insertIncomeData(TransactionType inc, String acntn);
     public QUERY_RESULT insertExpenseData(TransactionType exp, String acntn);
     public QUERY_RESULT updateIncomeData(int incID, TransactionType inc);
     public QUERY_RESULT updateExpenseData(int expID, TransactionType exp);
     public QUERY_RESULT deleteIncomeData(int incID);
     public QUERY_RESULT deleteExpenseData(int expID);
     public ArrayList<TransactionType> getIncomeData(String date, String acntName);
     public ArrayList<TransactionType> getExpenseData(String date, String acntName);
     public ArrayList<TransactionType> getListOfIncomes(String AcntName);
     public ArrayList<TransactionType> getListOfExpenses(String AcntName);
     public ArrayList<TransactionCollection> getTransactionData(String acntName);
}
