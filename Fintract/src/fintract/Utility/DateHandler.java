/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintract.Utility;

/**
 *
 * @author hunter
 */
public class DateHandler {
    private static String getMonthFromAnalyzer(String s){
        return switch(s){
            case "1","01" -> "Jan";
            case "2","02" -> "Feb";
            case "3","03" -> "Mar";
            case "4","04" -> "Apr";
            case "5","05" -> "May";
            case "6","06" -> "Jun";
            case "7","07" -> "Jul";
            case "8","08" -> "Aug";
            case "9","09" -> "Sept";
            case "10" -> "Oct";
            case "11" -> "Nov";
            case "12" -> "Dec";
            default -> s;
        };
    }
    
    private static String sanitizer(String m){
        return switch(m){
            case "Jan","jan" -> "Jan";
            case "Feb","feb" -> "Feb";
            case "Mar","mar" -> "Mar";
            case "Apr","apr" -> "Apr";
            case "May","may" -> "May";
            case "Jun","jun" -> "Jun";
            case "Jul","jul" -> "Jul";
            case "Aug","aug" -> "Aug";
            case "Sept","sept" -> "Sept";
            case "Oct","oct" -> "Oct";
            case "Nov","nov" -> "Nov";
            case "Dec", "dec" -> "Dec";
            default -> "IAV";
        };
    }
    
    public static String getMonth(String m){
        String[] date = m.split("-");
        return getMonthFromAnalyzer(date[0]);
    }
    
    public static String getMONTH(String m){
        String[] date = m.split(" ");
        return getMonthFromAnalyzer(date[0]);
    }
    
    public static String getSanitizeMonth(String m){
        return sanitizer(getMonthFromAnalyzer(m));
    }
    
    public static String getMonthDay(String d){
        String[] date = d.split("-");
        return getMonthFromAnalyzer(date[0])+" "+date[1];
    }
    
    public static String getMDay(String d){
        String[] date = d.split(" ");
        return sanitizer(getMonthFromAnalyzer(date[0]))+" "+date[1];
    }
    
    public static String getYear(String d){
        String[] date = d.split("-");
        return date[2];
    }
}
