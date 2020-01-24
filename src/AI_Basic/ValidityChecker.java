/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI_Basic;

/**
 *
 * @author acbar
 */
public class ValidityChecker {
    
    public static String runChecks(String input){
        String error = null;
        
        String[] text = splitText(input);
        
        if(text.length == 1 && text[0].isEmpty()){
            error = "No input detected.";
        }else if(text.length < 3){
            error = "Not enough information was input.";
        }else if(text.length > 3){
            error = "Too much information was input.";
        }else{
            switch(text[0]){
                case "M&C":
                    error = mAndC(text);
                    break;
                case "8puzzle":
                    error = eightPuzzle(text);
                    break;
                default:
                    error = "Game does not exist.";
                    break;
            }
        }
        
        return error;
    }
    
    private static boolean isInteger(String input) {
        try{
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    private static boolean isZeroToEight(String num){              
        if(num.length() == 9
                && num.contains("0")
                && num.contains("1")
                && num.contains("2")
                && num.contains("3")
                && num.contains("4")
                && num.contains("5")
                && num.contains("6")
                && num.contains("7")
                && num.contains("8")){
            return true;
        }else{
            return false;
        }
    }
    
    private static String[] splitText(String input){
        return input.split("#");
    }
    
    private static String mAndC(String[] input){
        String error = null;
        
        if(isInteger(input[1]) == false
                || input[1].length() != 6){
            error = "The second data item must be an integer with 6 digits.";
        }else if(isInteger(input[2]) == false
                || input[2].length() != 6){
            error = "The third data item must be an integer with 6 digits.";
        }
        
        return error;
    }
    
    private static String eightPuzzle(String[] input){
        String error = null;
        
        if(isInteger(input[1]) == false
                || input[1].length() != 9
                || isZeroToEight(input[1]) == false){
            error = "The second data item must be an integer with 9 digits "
                    + "and must contain the numbers 0-8.";
        }else if(isInteger(input[2]) == false
                || input[2].length() != 9
                || isZeroToEight(input[2]) == false){
            error = "The third data item must be an integer with 9 digits "
                    + "and must contain the numbers 0-8.";
        }
        
        return error;
    }
    
}
