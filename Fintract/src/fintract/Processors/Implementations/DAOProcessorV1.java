/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Processors.Implementations;

import fintract.Arch.Model.RawData.TransactionCollection;
import fintract.Arch.Model.RawData.TransactionType;
import fintract.DAO.Queries.CRUDQs;
import fintract.DAO.Queries.DBPropsQs;
import fintract.Factory.TransactionFactory;
import fintract.Processors.DAOProcessor;
import fintract.Utility.DateHandler;
import fintract.Utility.DateTimeHandler;
import fintract.Utility.ENUM_TYPES.ERROR_TYPE;
import fintract.Utility.ENUM_TYPES.QUERY_RESULT;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static fintract.Utility.ENUM_TYPES.QUERY_RESULT.ERROR_OCCURED;
import static fintract.Utility.ENUM_TYPES.QUERY_RESULT.SUCCESFULL_INSERTION;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author hunter
 */
public class DAOProcessorV1 implements DAOProcessor{
    private Connection conn;
    
    public DAOProcessorV1(){
        connectDB();
    }
    
    private void connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DBPropsQs.URL.getQuery());
        } catch (SQLException ex) {
            ERROR_TYPE.CONNECTION_FAILED.printMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOProcessorV1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getSearchedName(String n){
        String name = "";
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.FETCH_ACCOUNTNAME_BYID.getQuery())){
            pps.setInt(1, getId(n));
            ResultSet rs = pps.executeQuery();
            while(rs.next()){
                name = rs.getString("ACCOUNT_NAME");
            }
        } catch (SQLException ex) {
            name="NAV";
        }
        return (name.isBlank() ? "NAV" : name);
    }
    
    private int getId(String n){
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.FETCH_ACCOUNTNAME_ID.getQuery())){
            pps.setString(1, n);
            ResultSet rs = pps.executeQuery();
            int id = 0;
            while(rs.next()){
                id = rs.getInt("ACNT_ID");
            }
            return id;
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    @Override
    public String insertAcntData(String n){
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.INSERT_ACCOUNT_DATA.getQuery())){
            pps.setString(1, n);
            pps.setString(2, DateTimeHandler.getCurrentDate(new Date()));
            pps.executeUpdate();
        } catch (SQLException ex) {
            if(ex.getErrorCode()==19)
                ERROR_TYPE.printCustomErrorMessage("ACCOUNT ALREADY EXIST");
            else
                ERROR_TYPE.printCustomErrorMessage("CAN'T ADD THAT ACCOUNT DUE TO AN ERROR");
            n="NAV";
        }
        return n;
    }
    
    private int insertaAcntNameThenGetID(String n){
        int ID = getId(n);
        if(ID==0){
            insertAcntData(n);
            ID = getId(n);
        }
        return ID;
    }

    @Override
    public QUERY_RESULT insertIncomeData(TransactionType inc, String acntn) {
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.INSERT_INCOME_DATA.getQuery())){
            pps.setInt(1,  insertaAcntNameThenGetID(acntn));
            pps.setString(2, inc.Description());
            pps.setDouble(3, inc.Amount());
            pps.setString(4, inc.MonthDay_Recorded());
            pps.setString(5, inc.Time_recorded());
            pps.setString(6, inc.Year_Recorded());
            pps.executeUpdate();
            return QUERY_RESULT.SUCCESFULL_INSERTION;
        } catch (SQLException ex) {
           // QUERY_RESULT.INSERT_FAILURE.printErrorMessage("INCOME DATA");
            return ERROR_OCCURED;
        }
    }

    @Override
    public QUERY_RESULT insertExpenseData(TransactionType exp, String acntn) {
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.INSERT_EXPENSE_DATA.getQuery())){
            pps.setInt(1,  insertaAcntNameThenGetID(acntn));
            pps.setString(2, exp.Description());
            pps.setDouble(3, exp.Amount());
            pps.setString(4, exp.MonthDay_Recorded());
            pps.setString(5, exp.Time_recorded());
            pps.setString(6, exp.Year_Recorded());
            pps.executeUpdate();
            return SUCCESFULL_INSERTION;
        } catch (SQLException ex) {
            //QUERY_RESULT.INSERT_FAILURE.printErrorMessage("EXPENSE DATA");
            return ERROR_OCCURED;
        }
    }

    @Override
    public QUERY_RESULT updateIncomeData(int incID, TransactionType inc) {
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.UPDATE_INCOME_DATA.getQuery())){
            pps.setString(1, inc.Description());
            pps.setDouble(2, inc.Amount());
            pps.setString(3, inc.MonthDay_Recorded());
            pps.setString(4, inc.Time_recorded());
            pps.setString(5, inc.Year_Recorded());
            pps.setInt(6, incID);
            pps.executeUpdate();
            return SUCCESFULL_INSERTION;
        } catch (SQLException ex) {
            return ERROR_OCCURED;
        }
    }

    @Override
    public QUERY_RESULT updateExpenseData(int expID, TransactionType exp) {
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.UPDATE_EXPENSE_DATA.getQuery())){
            pps.setString(1, exp.Description());
            pps.setDouble(2, exp.Amount());
            pps.setString(3, exp.MonthDay_Recorded());
            pps.setString(4, exp.Time_recorded());
            pps.setString(5, exp.Year_Recorded());
            pps.setInt(6, expID);
            pps.executeUpdate();
            return SUCCESFULL_INSERTION;
        } catch (SQLException ex) {
            return ERROR_OCCURED;
        }
    }

    @Override
    public QUERY_RESULT deleteIncomeData(int incID) {
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.DELETE_INCOME_DATA.getQuery())){
            pps.setInt(1, incID);
            pps.executeUpdate();
            return SUCCESFULL_INSERTION;
        } catch (SQLException ex) {
            return ERROR_OCCURED;
        }
    }

    @Override
    public QUERY_RESULT deleteExpenseData(int expID) {
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.DELETE_EXPENSE_DATA.getQuery())){
            pps.setInt(1, expID);
            pps.executeUpdate();
            return SUCCESFULL_INSERTION;
        } catch (SQLException ex) {
            return ERROR_OCCURED;
        }
    }
    
    @Override
    public ArrayList<TransactionType> getIncomeData(String monthDay, String acntName) {
        ArrayList<TransactionType> incs = new ArrayList<>();
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.FETCH_INCOME_DATA_BY_MONTH.getQuery())){
            pps.setString(1, acntName);
            ResultSet rs = pps.executeQuery();
            while(rs.next()){
                incs.add(TransactionFactory.createIncome(
                        rs.getInt("INC_ID"),
                        rs.getString("Description"),
                        rs.getDouble("Amount"),
                        rs.getString("MonthDay_Recorded"),
                        rs.getString("Time_Recorded"),
                        rs.getString("Year_Recorded"))
                );
            }
            return sanitizedList(incs,monthDay);
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<TransactionType> getExpenseData(String monthday, String acntName) {
        ArrayList<TransactionType> exps = new ArrayList<>();
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.FETCH_EXPENSE_DATA_BY_MONTH.getQuery())){
            pps.setString(1, acntName);
            ResultSet rs = pps.executeQuery();
            while(rs.next()){
                exps.add(TransactionFactory.createExpense(
                        rs.getInt("EXP_ID"),
                        rs.getString("Description"),
                        rs.getDouble("Amount"),
                        rs.getString("MonthDay_Recorded"),
                        rs.getString("Time_Recorded"),
                        rs.getString("Year_Recorded"))
                );
            }
            return sanitizedList(exps,monthday);
        } catch (SQLException ex) {
            return null;
        }
    }

    private String acntName;
    @Override
    public ArrayList<TransactionCollection> getTransactionData(String acntName) {
        ArrayList<TransactionCollection> collections = new ArrayList<>();
        ArrayList<TransactionType> incs = getListOfIncomes(acntName);
        ArrayList<TransactionType> exps = getListOfExpenses(acntName);
        collections.add(new TransactionCollection(acntName,incs,exps));
        return collections;
    }
    
    @Override
    public ArrayList<TransactionType> getListOfIncomes(String AcntName){
        acntName="";
        ArrayList<TransactionType> incs = new ArrayList<>();
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.FETCH_ALL_INCOME_DATA.getQuery())){
            pps.setString(1, AcntName);
            ResultSet rs = pps.executeQuery();
             while(rs.next()){
                acntName = rs.getString("Account_Name");
                incs.add(TransactionFactory.createIncome(
                        rs.getInt("INC_ID"),
                        rs.getString("Description"),
                        rs.getDouble("Amount"),
                        rs.getString("MonthDay_Recorded"),
                        rs.getString("Time_Recorded"),
                        rs.getString("Year_Recorded"))
                );
             }
             return incs;
        } catch (SQLException ex) {
             return null;
        }
    }
    
    @Override
    public ArrayList<TransactionType> getListOfExpenses(String AcntName){
       ArrayList<TransactionType> exps = new ArrayList<>();
        try (PreparedStatement pps = conn.prepareStatement(CRUDQs.FETCH_ALL_EXPENSE_DATA.getQuery())){
             pps.setString(1, AcntName);
             ResultSet rs = pps.executeQuery();
             while(rs.next()){
                exps.add(TransactionFactory.createExpense(
                        rs.getInt("EXP_ID"),
                        rs.getString("Description"),
                        rs.getDouble("Amount"),
                        rs.getString("MonthDay_Recorded"),
                        rs.getString("Time_Recorded"),
                        rs.getString("Year_Recorded"))
                );
             }
             return exps;
        } catch (SQLException ex) {
             return null;
        }
    }
    
    private ArrayList<TransactionType> sanitizedList(ArrayList<TransactionType> unsanitizedList, String m){
        ArrayList<TransactionType> sanitizedList = new ArrayList<>();
        for(TransactionType type: unsanitizedList){
            String[] mday = type.MonthDay_Recorded().split(" ");
            if(m.equals(DateHandler.getMonth(mday[0]))) 
                sanitizedList.add(type);
        }
        return sanitizedList;
    }
}
