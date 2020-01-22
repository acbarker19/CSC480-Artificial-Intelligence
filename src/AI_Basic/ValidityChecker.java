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
    
    public static String mAndC(String possible){
        String error = null;
        
        String[] text = splitText(possible);

        if(text.length == 1 && text[0].isEmpty()){
            error = "No input detected.";
        }else if(text.length < 3){
            error = "Not enough information was input.";
        }else if(text.length > 3){
            error = "Too much information was input.";
        }else if(!text[0].equals("M&C")){
            error = "The first data item must be \"M&C\".";
        }else if(isInteger(text[1]) == false
                || text[1].length() != 6){
            error = "The second data item must be an integer with 6 digits.";
        }else if(isInteger(text[2]) == false
                || text[2].length() != 6){
            error = "The third data item must be an integer with 6 digits.";
        }
        
        return error;
    }
    
    public static String eightPuzzle(String possible){
        String error = null;
        
        String[] text = splitText(possible);

        if(text.length == 1 && text[0].isEmpty()){
            error = "No input detected.";
        }else if(text.length < 3){
            error = "Not enough information was input.";
        }else if(text.length > 3){
            error = "Too much information was input.";
        }else if(!text[0].equals("8puzzle")){
            error = "The first data item must be \"8puzzle\".";
        }else if(isInteger(text[1]) == false
                || text[1].length() != 9
                || isZeroToEight(text[1]) == false){
            error = "The second data item must be an integer with 9 digits "
                    + "and must contain the numbers 0-8.";
        }else if(isInteger(text[2]) == false
                || text[2].length() != 9
                || isZeroToEight(text[2]) == false){
            error = "The third data item must be an integer with 9 digits "
                    + "and must contain the numbers 0-8.";
        }
        
        return error;
    }
    
}
