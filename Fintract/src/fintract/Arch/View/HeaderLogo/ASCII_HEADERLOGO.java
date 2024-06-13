/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package fintract.Arch.View.HeaderLogo;

import fintract.Utility.Colors.ConsoleColor;
import static fintract.Utility.Colors.ConsoleColor.YELLOW;
import fintract.Utility.ENUM_TYPES.ERROR_TYPE;
import static fintract.Utility.Colors.ConsoleColor.GRAY;

/**
 *
 * @author hunter
 */
public enum ASCII_HEADERLOGO {
     //READABLE
     LOGO1("\n\u001B[1;92m#   ▒█▀▀▀ ▀█▀ ▒█▄░▒█ ▀▀█▀▀ ▒█▀▀█ ░█▀▀█ ▒█▀▀█ ▀▀█▀▀ "
            + "\n\u001B[1;92m#   ▒█▀▀▀ ▒█░ ▒█▒█▒█ ░▒█░░ ▒█▄▄▀ ▒█▄▄█ ▒█░░░ ░▒█░░ "
            + "\n\u001B[1;92m#   ▒█░░░ ▄█▄ ▒█░░▀█ ░▒█░░ ▒█░▒█ ▒█░▒█ ▒█▄▄█ ░▒█░░ "
            + "\n\u001B[1;92m# ▐================================================▌\n"
            + "             Coded by \u001B[38;2;0;191;255m@JLTC\u001B[1;92m \033[1;37m▐ \u001B[38;2;255;13;13mv1.0-BETA \u001B[0m\n\n"),
     //UN-READABLE
     LOGO2("""
           \u001b[1;92m#   \u2592\u2588\u2580\u2580\u2580 \u2580\u2588\u2580 \u2592\u2588\u2584\u2591\u2592\u2588 \u2580\u2580\u2588\u2580\u2580 \u2592\u2588\u2580\u2580\u2588 \u2591\u2588\u2580\u2580\u2588 \u2592\u2588\u2580\u2580\u2588 \u2580\u2580\u2588\u2580\u2580 
           \u001b[1;92m#   \u2592\u2588\u2580\u2580\u2580 \u2592\u2588\u2591 \u2592\u2588\u2592\u2588\u2592\u2588 \u2591\u2592\u2588\u2591\u2591 \u2592\u2588\u2584\u2584\u2580 \u2592\u2588\u2584\u2584\u2588 \u2592\u2588\u2591\u2591\u2591 \u2591\u2592\u2588\u2591\u2591 
           \u001b[1;92m#   \u2592\u2588\u2591\u2591\u2591 \u2584\u2588\u2584 \u2592\u2588\u2591\u2591\u2580\u2588 \u2591\u2592\u2588\u2591\u2591 \u2592\u2588\u2591\u2592\u2588 \u2592\u2588\u2591\u2592\u2588 \u2592\u2588\u2584\u2584\u2588 \u2591\u2592\u2588\u2591\u2591 
           \u001b[1;92m# \u2590================================================\u258c
                          Coded by \u001b[38;2;0;191;255m@JLTC\u001b[1;92m \u2551 \u001b[38;2;255;13;13mv1.0-BETA \u001b[0m
           
           """);
     
    private final String logo;
    
    private ASCII_HEADERLOGO(String logo){
        this.logo = logo;
    }
    
    private String getLogo(){
        return this.logo;
    }
    
    public static void printLogo(int i){
        switch(i){
            case 0 ->{
                System.out.print(ASCII_HEADERLOGO.LOGO1.getLogo());
                System.out.println(YELLOW.getColor()+"[GUIDE]>"+GRAY.getColor()+" To View Commands type [h] or [help]\n");
            }case 1 ->{
                System.out.print(ASCII_HEADERLOGO.LOGO2.getLogo());
                System.out.println(YELLOW.getColor()+"[GUIDE]>"+GRAY.getColor()+" To View Commands type [h] or [help]\n");
            }default ->
                ERROR_TYPE.printCustomErrorMessage("LOGO #"+i+" does not exist");
        }
    }
}
