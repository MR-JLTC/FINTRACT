/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package fintract.DAO.Queries;

/**
 *
 * @author hunter
 */
public enum CRUDQs {
    INSERT_INCOME_DATA("""
                       INSERT INTO INCOME(ACNT_ID, Description, Amount, MonthDay_Recorded, Time_Recorded, Year_Recorded) VALUES(?,?,?,?,?,?)
                       """),
    INSERT_EXPENSE_DATA("""
                        INSERT INTO EXPENSE(ACNT_ID, Description, Amount, MonthDay_Recorded, Time_Recorded, Year_Recorded) VALUES(?,?,?,?,?,?)
                        """),
    INSERT_ACCOUNT_DATA("""
                        INSERT INTO ACCOUNT_INFO(Account_Name, Recorded_Date) values(?,?)
                        """),
    UPDATE_INCOME_DATA("""
                       UPDATE INCOME SET Description=?, Amount=?, MonthDay_Recorded=?, Time_Recorded=?, Year_Recorded=? WHERE INC_ID=?
                       """),
    UPDATE_EXPENSE_DATA("""
                       UPDATE EXPENSE SET Description=?, Amount=?, MonthDay_Recorded=?, Time_Recorded=?, Year_Recorded=? WHERE EXP_ID=?
                       """),
    FETCH_ACCOUNTNAME_ID("SELECT ACNT_ID FROM ACCOUNT_INFO WHERE ACCOUNT_NAME=?"),
    FETCH_ACCOUNTNAME_BYID("SELECT ACCOUNT_NAME FROM ACCOUNT_INFO WHERE ACNT_ID=?"),
    FETCH_INCOME_DATA_BY_MONTH("SELECT * FROM INCOME_INFO WHERE Account_Name=?"),
    FETCH_EXPENSE_DATA_BY_MONTH("SELECT * FROM EXPENSE_INFO WHERE Account_Name=?"),
    FETCH_ALL_INCOME_DATA_WCY("SELECT * FROM INCOME_INFO WHERE Year_Recorded=?"),
    FETCH_ALL_EXPENSE_DATA_WCY("SELECT * FROM INCOME_INFO WHERE Year_Recorded=?"),
    FETCH_ALL_INCOME_DATA("SELECT * FROM INCOME_INFO WHERE Account_Name=?"),
    FETCH_ALL_EXPENSE_DATA("SELECT * FROM EXPENSE_INFO WHERE Account_Name=?"),
    DELETE_EXPENSE_DATA("DELETE FROM INCOME WHERE INC_ID=?"),
    DELETE_INCOME_DATA("DELETE FROM EXPENSE WHERE EXP_ID=?");
    
    private final String q;
    CRUDQs(String q){
        this.q = q;
    }
    
    public String getQuery(){
        return this.q;
    }
}
