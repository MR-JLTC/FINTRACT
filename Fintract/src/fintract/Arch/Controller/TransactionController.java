/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Arch.Controller;

import fintract.Arch.Model.RawData.TransactionType;
import fintract.Arch.View.ViewManager.ViewManager;
import fintract.DAOExecutor.TransactionDAOExecutor;
import fintract.Factory.TransactionFactory;
import fintract.Service.CMDServiceProcessorExecutor;
import fintract.Utility.CMDFileReader;
import fintract.Utility.DateHandler;
import fintract.Utility.DateTimeHandler;
import fintract.Utility.ENUM_TYPES.COMMAND_TYPE;
import static fintract.Utility.ENUM_TYPES.COMMAND_TYPE.*;
import fintract.Utility.ENUM_TYPES.TYPE;
import static fintract.Utility.ENUM_TYPES.TYPE.EXPENSE;
import static fintract.Utility.ENUM_TYPES.TYPE.INCOME;
import java.io.IOException;
import javax.swing.JOptionPane;
import static fintract.Utility.ENUM_TYPES.VIEW_TYPE.*;
import java.util.Date;


/**
 *
 * @author hunter
 */
public class TransactionController{
    private final CMDServiceProcessorExecutor cmdServiceProcessorHandler;
    private final TransactionDAOExecutor dao_processor;
    private final ViewManager view_manager;
    private boolean isOnAccount=false;
    private String acnt_name;
    private String[] cmd_data;
    
    public TransactionController(CMDServiceProcessorExecutor service, TransactionDAOExecutor daoHandler, ViewManager viewManager){
        this.cmdServiceProcessorHandler = service;
        this.dao_processor = daoHandler;
        this.view_manager = viewManager;
    }
    
    private void clearScreen(){
        try{
            if(System.getProperty("os.name").contains("Windows")){
              new ProcessBuilder("cmd","/c", "cls").inheritIO().start().waitFor();
            }else{
              Runtime.getRuntime().exec("clear").waitFor();
            }
            view_manager.printHeaderLogo();
        }catch(IOException | InterruptedException e){
            JOptionPane.showMessageDialog(null,"System clear interrupted","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void execDiffAddingProcess(){
       COMMAND_TYPE type = cmdServiceProcessorHandler.addTypeCMD(cmd_data);
       if(isOnAccount){
           switch(type){
               case ADD_ACNT ->{
                    view_manager.outputErrorMessage("Command Execution Denied");
                    view_manager.outputInfoMessage("Please go back to main first to create an account");
               }
               case ADD_INC_DATA ->{
                   TransactionType income;
                   String monthday="",time="",yr="";
                    if(cmd_data[5].equals("today")) {
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getCurrentDate(new Date()));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getCurrentDate(new Date()));
                    }else{
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getChosenDate(cmd_data[5]));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getChosenDate(cmd_data[5]));
                    }
                    income = TransactionFactory.createIncome(cmd_data[2], Double.parseDouble(cmd_data[3]), monthday, time, yr);
                    dao_processor.insertData(acnt_name, income, TYPE.INCOME);
               }
               case ADD_EXP_DATA -> {
                   TransactionType expense;
                   String monthday="",time="",yr="";
                    if(cmd_data[5].equals("today")) {
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getCurrentDate(new Date()));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getCurrentDate(new Date()));
                    }else{
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getChosenDate(cmd_data[5]));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getChosenDate(cmd_data[5]));
                    }
                    expense = TransactionFactory.createExpense(cmd_data[2], Double.parseDouble(cmd_data[3]), monthday, time, yr);
                    dao_processor.insertData(acnt_name, expense, TYPE.EXPENSE);
               }
               case NO_ARGUMENTS ->
                   view_manager.outputErrorMessage("Missing Arguments");
               default -> 
                   view_manager.outputErrorMessage("INVALID COMMAND");
           }
      }else{
           switch(type){
            case ADD_ACNT -> {
                cmd_data = cmdServiceProcessorHandler.getData();
                acnt_name = dao_processor.insertAccountData(cmd_data[0]);
                isOnAccount = true;
            }
            case ADD_INC_DATA,ADD_EXP_DATA ->{
                view_manager.outputErrorMessage("Command Execution Denied");
                view_manager.outputInfoMessage("Please use or create an account first");
            }
            case NO_ARGUMENTS ->
                   view_manager.outputErrorMessage("Missing Arguments");
            default -> 
                view_manager.outputErrorMessage("INVALID COMMAND");
        }
       }
    }
    
    private void execDiffDeleteProcess(){
        switch(cmdServiceProcessorHandler.delete(cmd_data)){
            case DELETE_EXP_DATA ->
                dao_processor.deleteData(Integer.parseInt(cmd_data[2]), EXPENSE);
            case DELETE_INC_DATA ->
                dao_processor.deleteData(Integer.parseInt(cmd_data[2]), INCOME);
            case INVALID ->
                view_manager.outputErrorMessage("INVALID COMMAND");
            case NO_ARGUMENTS ->
                view_manager.outputErrorMessage("Missing Arguments");
        }
    }
    
    private void execDiffUpdateProcess(){
        switch(cmdServiceProcessorHandler.edit(cmd_data)){
            case UPDATE_INC_DATA ->{
                   TransactionType income;
                   String monthday="",time="",yr="";
                    if(cmd_data[6].equals("today")) {
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getCurrentDate(new Date()));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getCurrentDate(new Date()));
                    }else{
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getChosenDate(cmd_data[6]));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getChosenDate(cmd_data[6]));
                    }
                    income = TransactionFactory.createIncome(cmd_data[3], Double.parseDouble(cmd_data[4]), monthday, time, yr);
                    dao_processor.updateData(Integer.parseInt(cmd_data[2]), income, INCOME);
            }
            case UPDATE_EXP_DATA ->{
                  TransactionType expense;
                   String monthday="",time="",yr="";
                    if(cmd_data[6].equals("today")) {
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getCurrentDate(new Date()));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getCurrentDate(new Date()));
                    }else{
                        monthday = DateHandler.getMonthDay(DateTimeHandler.getChosenDate(cmd_data[6]));
                        time = DateTimeHandler.getCurrentTimeInString(new Date());
                        yr = DateHandler.getYear(DateTimeHandler.getChosenDate(cmd_data[6]));
                    }
                    expense = TransactionFactory.createIncome(cmd_data[3], Double.parseDouble(cmd_data[4]), monthday, time, yr);
                    dao_processor.updateData(Integer.parseInt(cmd_data[2]), expense, EXPENSE);
            }
            case INVALID ->
                view_manager.outputErrorMessage("INVALID COMMAND");
        }
    }

    private void execDiffFetchProcess() { 
        String date="";
        try{
        if(cmd_data[3].equals("today"))
            date = DateHandler.getMonth(DateTimeHandler.getCurrentDate(new Date()));
        else
            date = DateHandler.getSanitizeMonth(cmd_data[3]);
        }catch(ArrayIndexOutOfBoundsException ae){
            date = DateHandler.getMonth(DateTimeHandler.getCurrentDate(new Date()));
        }
        
        switch(cmdServiceProcessorHandler.fetchDataForReport(cmd_data)){
            case FETCH_INCOME_DATA ->
                view_manager.displayDataInTableFormat(OUTPUT_INCOMES_BYMONTH_VIEWER, dao_processor.getTransactions(TYPE.INCOME, date, acnt_name));
            case FETCH_EXPENSE_DATA ->
                view_manager.displayDataInTableFormat(OUTPUT_EXPENSES_BYMONTH_VIEWER, dao_processor.getTransactions(TYPE.EXPENSE, date, acnt_name));
            case FETCH_ALL_INCOME_DATA ->
               view_manager.displayDataInTableFormat(OUTPUT_INCOMES_BYCURRENTYEAR_VIEWER, dao_processor.getTransactions(TYPE.ALL_INCOMES, date, acnt_name));
            case FETCH_ALL_EXPENSE_DATA ->
                view_manager.displayDataInTableFormat(OUTPUT_EXPENSES_BYCURRENTYEAR_VIEWER,dao_processor.getTransactions(TYPE.ALL_EXPENSES, date, acnt_name));
            case FETCH_ALL_TRANSACTIONS ->
                view_manager.displayDataInTableFormat(ALL_EXPS_INCS_VIEWER, dao_processor.getTransactions(TYPE.TRANSACTIONLIST, date, acnt_name));
            case NO_ARGUMENTS ->
                view_manager.outputErrorMessage("Missing Arguments");
            default ->
                view_manager.outputErrorMessage("INVALID COMMAND");
        }
    }

    private void execDiffTypesOfDOCGeneration() {
        switch(cmdServiceProcessorHandler.generateDocs(cmd_data)){
            case GENERATE_DOCS_BYMONTH -> 
                view_manager.outputInfoMessage("GENERATING DOCS BY MONTH PROCESS");
            case GENERATE_DOCS_ALL_TRANSACTIONS ->
                view_manager.outputInfoMessage("GENERATING DOCS FOR ALL TRANSACTIONS WITHIN CURRENT YEAR PROCESS");
            case GENERATE_DOCS_ALL_TRANSACTIONS_BYACCOUNTANDMONTH ->
                view_manager.outputInfoMessage("GENERATING DOCS FOR ALL TRANSACTIONS WITHIN ACCOUNT AND MONTH PROCESS");
            case GENERATE_DOCS_ALL_TRANSACTIONS_INACCOUNT ->
                view_manager.outputInfoMessage("GENERATING DOCS FOR ALL TRANSACTIONS WITHIN ACCOUNT PROCESS");
            case NO_ARGUMENTS ->
                view_manager.outputErrorMessage("Missing Arguments");
            case NO_RESOURCES->
                view_manager.outputInfoMessage("This feature is not currently available, maybe in the future patch");
            default ->
                view_manager.outputErrorMessage("INVALID COMMAND");
        }
    }
    
    public void start(){
        clearScreen();
        while(true){
            if(isOnAccount){
                cmd_data = view_manager.getInputFromAccount(acnt_name).split(" ");
                switch(cmdServiceProcessorHandler.processType(cmd_data)){
                    case ADD_ACNT,USE_ACCOUNT -> {
                        view_manager.outputErrorMessage("Command Execution Denied");
                        view_manager.outputInfoMessage("Please go back to main page first,\n     press [x] or [q] if wish to proceed");
                        view_manager.outputInfoMessage("Maybe I'll implement it in the future patch");
                    }
                    case ADD_INC_DATA,ADD_EXP_DATA ->
                        execDiffAddingProcess();
                    case CLEAR -> 
                        start();
                    case HELP_WD -> 
                        CMDFileReader.printCmdList(CMD_LIST_WD);
                    case HELP -> 
                        CMDFileReader.printCmdList(CMD_LIST);
                    case EXIT -> {
                        isOnAccount = false;
                        clearScreen();
                    }
                    case DELETE -> 
                        execDiffDeleteProcess();
                    case UPDATE -> 
                        execDiffUpdateProcess();
                    case FETCH_FOR_REPORT -> 
                        execDiffFetchProcess();
                    case FETCH_FOR_DOCSGENERATION -> 
                        execDiffTypesOfDOCGeneration();
                    case NO_ARGUMENTS ->
                        view_manager.outputErrorMessage("Missing Arguments");
                    default -> 
                        view_manager.outputErrorMessage("INVALID COMMAND");
                }
            }else{
                cmd_data = view_manager.getInputFromMain().split(" ");
                switch(cmdServiceProcessorHandler.processType(cmd_data)){
                    case ADD_ACNT -> 
                        execDiffAddingProcess();
                    case USE_ACCOUNT -> {
                        String val_r = dao_processor.getSearchedName(cmd_data[2]);
                        switch(val_r){
                            case "NAV" -> {
                                view_manager.outputErrorMessage("Account Not Found");
                                isOnAccount=false;
                            }
                            default -> {
                                acnt_name = val_r;
                                isOnAccount = true;
                            }
                        }
                    }
                    case CLEAR -> 
                        clearScreen();
                    case HELP_WD -> 
                        CMDFileReader.printCmdList(CMD_LIST_WD);
                    case HELP -> 
                        CMDFileReader.printCmdList(CMD_LIST);
                    case EXIT -> System.exit(0);
                    case DELETE,UPDATE,
                         ADD_INC_DATA,
                         ADD_EXP_DATA,
                         FETCH_FOR_REPORT,
                         FETCH_FOR_DOCSGENERATION ->{
                         view_manager.outputErrorMessage("Command Execution Denied");
                         view_manager.outputInfoMessage("Please use or create an account first");
                    }
                    case NO_ARGUMENTS ->
                        view_manager.outputErrorMessage("Missing Arguments");
                    default -> {
                        System.out.println("EXECUTED HERE");
                        view_manager.outputErrorMessage("INVALID COMMAND");
                    }
                }
            }
        }
    }
}
