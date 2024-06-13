package fintract.Factory;

import fintract.Arch.Model.RawData.TransactionType;
import fintract.Arch.Model.RawData.TransactionCollection;
import java.util.ArrayList;

public class TransactionFactory{
    //For database fetching
    public static TransactionType createIncome(int incid, String desc, double amnt, String md_r, String time, String y_r){
        return new TransactionType(incid, desc, amnt, md_r, time, y_r);
    }
    
    public static TransactionType createExpense(int expid, String desc, double amnt, String md_r, String time, String y_r){
        return new TransactionType(expid, desc, amnt, md_r, time, y_r);
    }
    
    //For inserting data into the database
    public static TransactionType createIncome(String desc, double amnt, String md_r, String time, String y_r){
        return new TransactionType(desc, amnt, md_r, time, y_r);
    }
    
    public static TransactionType createExpense(String desc, double amnt, String md_r, String time, String y_r){
        return new TransactionType(desc, amnt, md_r, time, y_r);
    }
    
    public static TransactionCollection createTransactionCollections(String acnt, String m, ArrayList<TransactionType> incs, ArrayList<TransactionType> exps){
        return new TransactionCollection(acnt, incs, exps);
    }
}