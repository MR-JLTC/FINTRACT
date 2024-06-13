/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package fintract.DAO.Queries;

/**
 *
 * @author hunter
 */
public enum DBPropsQs {
    URL("jdbc:sqlite:Resources/TransactionRecord.db"),
    CREATE_INCOME_TABLE("""
                        CREATE TABLE IF NOT EXISTS INCOME(
                            INC_ID integer unique primary key autoincrement,
                            ACNT_ID integer,
                            Description varchar(100),
                            Amount double(10,2),
                            MonthDay_Recorded varchar(30),
                            Time_Recorded varchar(30),
                            Year_Recorded varchar(30),
                            foreign key(ACNT_ID) references ACCOUNT_INFO(ACNT_ID)
                        );
                        """),
    CREATE_EXPENSE_TABLE("""
                         CREATE TABLE IF NOT EXISTS EXPENSE(
                            EXP_ID integer unique primary key autoincrement,
                            ACNT_ID integer,
                            Description varchar(100),
                            Amount double(10,2),
                            MonthDay_Recorded varchar(30),
                            Time_Recorded varchar(30),
                            Year_Recorded varchar(30),
                            foreign key(ACNT_ID) references ACCOUNT_INFO(ACNT_ID)
                         );
                         """),
    
    CREATE_ACCOUNT_TABLE("""
                         CREATE TABLE IF NOT EXISTS ACCOUNT_INFO(
                            ACNT_ID integer unique primary key autoincrement,
                            AcountName varchar(30),
                            Recorded_Date varchar(30)
                         );
                         """),
    
    CREATE_INCOME_VIEW("""
                       CREATE VIEW IF NOT EXISTS INCOME_INFO AS
                       SELECT INC_ID, ACCOUNT_INFO.ACCOUNT_NAME, DESCRIPTION.
                       AMOUNT, MONTHDAY_RECORDED, TIME_RECORDED, YEAR_RECORDED FROM INCOME
                       INNER JOIN ACCOUNT_INFO ON INCOME_ACNT_ID=ACCOUNT_INFO.ACNT_ID;
                       """),
    
    CREATE_EXPENSE_VIEW("""
                        CREATE VIEW IF NOT EXISTS EXPENSE_INFO AS
                        SELECT EXP_ID, ACCOUNT_INFO.ACCOUNT_NAME, DESCRIPTION.
                        AMOUNT, MONTHDAY_RECORDED, TIME_RECORDED, YEAR_RECORDED FROM EXPENSE
                        INNER JOIN ACCOUNT_INFO ON EXPENSE_ACNT_ID=ACCOUNT_INFO.ACNT_ID;                    
                        """);
    
    private final String q;
    DBPropsQs(String q){
        this.q = q;
    }
    
    public String getQuery(){
        return this.q;
    }
}
