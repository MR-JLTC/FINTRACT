    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package fintract.Utility.ENUM_TYPES;

/**
 *
 * @author hunter
 */
public enum COMMAND_TYPE {
    NAC, //No assigned command
    ADD,
    DELETE,
    UPDATE,
    FETCH_FOR_REPORT,
    FETCH_FOR_DOCSGENERATION,
    USE_ACCOUNT,
    CLEAR,
    HELP_WD,
    HELP,
    EXIT,
    INVALID,
    NO_ARGUMENTS,
    NO_RESOURCES,
    //ADDING TYPES
    ADD_ACNT,
    ADD_EXP_DATA,
    ADD_INC_DATA,
    
    //TYPES OF DELETION
    DELETE_INC_DATA,
    DELETE_EXP_DATA,
    
    //TYPES OF UPDATE
    UPDATE_INC_DATA,
    UPDATE_EXP_DATA,
    
    //TYPES OF FETCH
    FETCH_INCOME_DATA,
    FETCH_EXPENSE_DATA,
    FETCH_ALL_INCOME_DATA,
    FETCH_ALL_EXPENSE_DATA,
    FETCH_ALL_TRANSACTIONS,
    
    //TYPES OF DOCS GENERATION
    GENERATE_DOCS_BYMONTH,
    GENERATE_DOCS_ALL_TRANSACTIONS,
    GENERATE_DOCS_ALL_TRANSACTIONS_BYACCOUNTANDMONTH,
    GENERATE_DOCS_ALL_TRANSACTIONS_INACCOUNT,
    
    //FOR CMD VIEW
    CMD_LIST_WD, //with description
    CMD_LIST; // without description
}
