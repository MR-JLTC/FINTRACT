/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Utility;

import fintract.Utility.ENUM_TYPES.ERROR_TYPE;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.sql.Time;
public class DateTimeHandler {
    public static String getCurrentTime(Date unparsedTime){
        //Time currentTime = null;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String FTime = format.format(unparsedTime);
        Date parsedTime = null;
        try {
             parsedTime = format.parse(FTime);
            //currentTime = new Time(parsedTime.getTime());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return format.format(parsedTime);
    }
    
    public static String getCurrentDate(Date unparsedDate){
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        String FDate = formatter.format(unparsedDate);
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(FDate);
        } catch (ParseException ex) {
            ERROR_TYPE.printCustomErrorMessage("ERROR OCCURED WHILE GETTING THE CURRENT DATE: "+ex.getMessage());
        }
        return formatter.format(parsedDate);
    }
    
    public static String getCurrentTimeInString(Date unparsedTime){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
        return format.format(unparsedTime);
    }
    
    public static String getChosenDate(String selectedDate){
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        try { 
            Date date = format.parse(selectedDate);
            return format.format(date);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
