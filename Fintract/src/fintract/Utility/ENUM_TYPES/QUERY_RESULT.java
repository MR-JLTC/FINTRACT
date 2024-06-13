/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package fintract.Utility.ENUM_TYPES;

/**
 *
 * @author hunter
 */
public enum QUERY_RESULT{
    ERROR_OCCURED,
    /* SUCCESSFUL CRUD OPERATIONS*/
    SUCCESFULL_INSERTION(SUCCESS_TYPE.SUCCESSFUL_INSERTION),
    SUCCESSFULY_FETCH(SUCCESS_TYPE.SUCESSFUL_FETCH),
    SUCCESSFUL_UPDATE(SUCCESS_TYPE.SUCESSFUL_UPDATE),
    SUCCESSFUL_DELETE(SUCCESS_TYPE.SUCESSFUL_DELETION),
    /* CRUD OPERATION FAILURES*/
    INSERT_FAILURE(ERROR_TYPE.INSERTIOIN_FAILURE),
    FETCH_FAILURE(ERROR_TYPE.FETCHING_FAILURE),
    UPDATE_FAILURE(ERROR_TYPE.UPDATE_FAILURE),
    DELETE_FAILURE(ERROR_TYPE.DELETION_FAILURE);
    
    private SUCCESS_TYPE s_type;
    private ERROR_TYPE e_type;
    
    QUERY_RESULT(SUCCESS_TYPE type) {
        this.s_type = type;
    }
    
    QUERY_RESULT(ERROR_TYPE type){
        this.e_type = type;
    }
    
    QUERY_RESULT(){}
    
    public void printErrorMessage(String m){
        e_type.printMessage(m);
    }
    
    public void printSuccessMessage(String m){
        s_type.printMessage(m);
    }
}
