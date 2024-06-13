/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Processors.Implementations;

import fintract.Utility.ENUM_TYPES.COMMAND_TYPE;
import static fintract.Utility.ENUM_TYPES.COMMAND_TYPE.*;
import fintract.Processors.CMDAnalyzerServiceProcessor;

/**
 *
 * @author hunter
 */
public class CMDAnalyzerServiceProcessorV1 implements CMDAnalyzerServiceProcessor {
    private String[] data;
    
    @Override
    public COMMAND_TYPE processType(String[] cmd_data){
        return switch(cmd_data[0]){
            case "a" -> addTypeProcess(cmd_data);
            case "d" -> DELETE;
            case "e" -> UPDATE;
            case "s" -> FETCH_FOR_REPORT;
            case "gd" -> FETCH_FOR_DOCSGENERATION;
            case "clear","c","cls" -> CLEAR;
            case "u" -> USE_ACCOUNT;
            case "h","help" -> cmdViewType(cmd_data);
            case "x","q" -> EXIT;
            default -> INVALID;
        };
    }
    
    private COMMAND_TYPE cmdViewType(String[] cmd_data){
        try{
            return switch(cmd_data[1]){
                case "-wd" -> HELP_WD;
                default -> INVALID;
            };
        }catch(ArrayIndexOutOfBoundsException npe){
            return HELP;
        }
    }

    @Override
    public COMMAND_TYPE addTypeProcess(String[] cmd_data) {
        COMMAND_TYPE type = NAC;
       try{ 
        switch(cmd_data[1]){
            case "acnt" ->{
                type = ADD_ACNT;
                data = new String[]{cmd_data[2]};
            }
            case "exp" ->{
                type = ADD_EXP_DATA;
                data = new String[]{cmd_data[2],cmd_data[3],cmd_data[4],cmd_data[5]};
            }
            case "inc" ->{
                type = ADD_INC_DATA;
                data = new String[]{cmd_data[2],cmd_data[3],cmd_data[4],cmd_data[5]};
            }
            default -> {
                type = INVALID;
            }
        }
       }catch(ArrayIndexOutOfBoundsException arp){
           data = cmd_data;
           type = getInvalidReason();
       }
       return type;
    }
    
    private COMMAND_TYPE getInvalidReason(){
        try{
           return switch(data[1]){
                   case "acnt","exp","inc" -> NO_ARGUMENTS;
                   default -> INVALID;
           };
        }catch(ArrayIndexOutOfBoundsException arp){
            return NO_ARGUMENTS;
        }
    }
    
    @Override
    public String[] getData(){
        return this.data;
    }

    @Override
    public COMMAND_TYPE deleteTypeProcess(String[] cmd_data) {
        try{
            return switch(cmd_data[1]){
                case "exp" -> DELETE_EXP_DATA;
                case "inc" -> DELETE_INC_DATA;
                default -> INVALID;
            };
        }catch(ArrayIndexOutOfBoundsException ab){
            return NO_ARGUMENTS;
        }
    }

    @Override
    public COMMAND_TYPE updateTypeProcess(String[] cmd_data) {
        try{
            return switch(cmd_data[1]){
                case "inc" -> UPDATE_INC_DATA;
                case "exp" -> UPDATE_EXP_DATA;
                default -> INVALID;
            };
        }catch(ArrayIndexOutOfBoundsException ab){
            return NO_ARGUMENTS;
        }
    }

    @Override
    public COMMAND_TYPE fetchTypeProcess(String[] cmd_data) {
        try{
            String cmd = cmd_data[1]+" "+cmd_data[2];
            return switch(cmd){
                case "inc --month" -> FETCH_INCOME_DATA;
                case "exp --month" -> FETCH_EXPENSE_DATA;
                case "inc -all" -> FETCH_ALL_INCOME_DATA;
                case "exp -all" -> FETCH_ALL_EXPENSE_DATA;
                case "-all tsn" -> FETCH_ALL_TRANSACTIONS;
                default -> INVALID;    
            };
        }catch(ArrayIndexOutOfBoundsException ab){
            return NO_ARGUMENTS;
        }
    }

    @Override
    public COMMAND_TYPE DocsGenerateTypeProcess(String[] cmd_data) {
        try{
            return switch(cmd_data[1]){
                case "--month" -> GENERATE_DOCS_BYMONTH;
                case "-all" -> GENERATE_DOCS_ALL_TRANSACTIONS;
                case "acnt" -> generationType(cmd_data[3]);
                default -> INVALID;    
            };
        }catch(ArrayIndexOutOfBoundsException ab){
            return NO_ARGUMENTS;
        }
    }
    
    private COMMAND_TYPE generationType(String c){
        try{
            return switch(c){
                case "--month" -> GENERATE_DOCS_ALL_TRANSACTIONS_BYACCOUNTANDMONTH;
                case "-all" -> GENERATE_DOCS_ALL_TRANSACTIONS_INACCOUNT;
                default -> NAC;
            };
        }catch(ArrayIndexOutOfBoundsException ab){
            return NO_ARGUMENTS;
        }
    }
}
