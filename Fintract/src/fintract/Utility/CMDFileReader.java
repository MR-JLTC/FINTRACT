/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Utility;

import fintract.Utility.Colors.ConsoleColor;
import fintract.Utility.ENUM_TYPES.COMMAND_TYPE;
import fintract.Utility.ENUM_TYPES.ERROR_TYPE;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hunter
 */
public class CMDFileReader {
    private static ArrayList<String> cmdList;
    private static String filepath;
    
    public static void printCmdList(COMMAND_TYPE t){
        filepath = "cmd_info/";
        cmdList = new ArrayList<>();
        switch(t){
            case CMD_LIST_WD -> readFile("sys_cmds_wd.txt");
            case CMD_LIST -> readFile("sys_cmds.txt");
            default -> ERROR_TYPE.printCustomErrorMessage("CMD File Not Found");
        }
        try{
            String rw[] = cmdList.get(0).split(" ");
            String cet = ConsoleColor.GRAY.getColor()+rw[0]+ConsoleColor.BLUE.getColor()+" "+rw[1]+" "+rw[2]+ConsoleColor.GRAY.getColor();
            cmdList.set(0, cet);
            System.out.println();
            for(String cmds_inRow: cmdList){
                System.out.println(cmds_inRow);
            }
            System.out.println();
        }catch(IndexOutOfBoundsException e){
            ERROR_TYPE.printCustomErrorMessage("ERROR OCCURED: "+e.getMessage());
        }
    }
    
    private static void readFile(String fn){
        filepath+=fn;
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String dataInRow;
            while((dataInRow=br.readLine()) != null){
                cmdList.add(dataInRow);
            }
            filepath="";
        }catch(IOException e){
            ERROR_TYPE.printCustomErrorMessage("ERROR OCCURED: "+e.getMessage());
        }
    }
}
