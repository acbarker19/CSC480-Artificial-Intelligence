/**
 * ValidityChecker.
 * A class that checks if the user input is in the correct format for each
 * problem.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class ValidityChecker {
    
    // Returns null if there are no errors.
    public static String runChecks(String input){
        String error = null;
        
        String[] text = splitText(input);
        
        if(text.length == 1 && text[0].isEmpty()){
            error = "No input detected.";
        }else{
            switch(text[0]){
                case "M&C":
                    error = mAndC(text);
                    break;
                case "8puzzle":
                    error = eightPuzzle(text);
                    break;
                case "Route":
                    error = routeFinder(text);
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
    
    private static int[] getIntArray(String input){
        int[] data = new int[input.length()];
        
        for(int i = 0; i < input.length(); i++){
            data[i] = Character.getNumericValue(input.charAt(i));
        }
        
        return data;
    }
    
    private static String mAndC(String[] input){
        String error = null;
        int[] initState = getIntArray(input[1]);
        int[] endState = getIntArray(input[2]);
        
        if(input.length < 3){
            error = "Not enough information was input.";
        }else if(input.length > 3){
            error = "Too much information was input.";
        }else if(isInteger(input[1]) == false ||
                input[1].length() != 6){
            error = "The second data item must be an integer with 6 digits.";
        }else if(isInteger(input[2]) == false ||
                input[2].length() != 6){
            error = "The third data item must be an integer with 6 digits.";
        }else if(input[1].contains("0010") ||
                input[1].contains("0100")){
            error = "The second data item has the boat in an unreachable position.";
        }else if(input[2].contains("0010") ||
                input[2].contains("0100")){
            error = "The third data item has the boat in an unreachable position.";
        }else if(!((getIntArray(input[1])[2] == 1 && getIntArray(input[1])[3] == 0) ||
                (getIntArray(input[1])[2] == 0 && getIntArray(input[1])[3] == 1))){
            error = "The second data item can only have one boat.";
        }else if(!((getIntArray(input[2])[2] == 1 && getIntArray(input[2])[3] == 0) ||
                (getIntArray(input[2])[2] == 0 && getIntArray(input[2])[3] == 1))){
            error = "The third data item can only have one boat.";
        }else if((initState[0] != 0 && initState[0] < initState[1]) ||
                (initState[4] != 0 && initState[4] < initState[5])){
            error = "The second data item has more cannibals than missionaries "
                    + "on one shore.";
        }else if((endState[0] != 0 && endState[0] < endState[1]) ||
                (endState[4] != 0 && endState[4] < endState[5])){
            error = "The third data item has more cannibals than missionaries "
                    + "on one shore.";
        }else if(initState[0] + initState[4] != endState[0] + endState[4]){
            error = "The second and third data items do not have an equal "
                    + "number of missionaries";
        }else if(initState[1] + initState[5] != endState[1] + endState[5]){
            error = "The second and third data items do not have an equal "
                    + "number of cannibals";
        }
        
        return error;
    }
    
    private static String eightPuzzle(String[] input){
        String error = null;
        
        if(input.length < 3){
            error = "Not enough information was input.";
        }else if(input.length > 3){
            error = "Too much information was input.";
        }else if(isInteger(input[1]) == false
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
    
    private static String routeFinder(String[] input){
        String error = null;
        
        if(input.length < 4){
            error = "Not enough information was input.";
        }else if(input.length > 4){
            error = "Too much information was input.";
        }else if(input[1].contains(" ")){
            error = "The second data item can't contain spaces.";
        }else if(input[2].contains(" ")){
            error = "The third data item can't contain spaces.";
        }else if(!input[3].contains("\n" + input[1] + " ") &&
                !input[3].contains(" " + input[1] + "\n")){
            error = "The second data item must be part of a route in the "
                    + "fourth data item.";
        }else if(!input[3].contains("\n" + input[2] + " ") &&
                !input[3].contains(" " + input[2] + "\n")){
            error = "The third data item must be part of a route in the "
                    + "fourth data item.";
        }else{
            String[] paths = input[3].split("\n");
            for(String path : paths){
                String[] route = path.split(" ");
                if(isInteger(route[1]) == false || route.length != 3){
                    error = "Every route in the fourth data item must be "
                            + "written as \"City-1 Distance City-2\", where "
                            + "Distance is an integer value. Each route should "
                            + "be on its own line except for the first one, "
                            + "which should be on the same line as the first "
                            + "three data items.";
                    break;
                }
            }
        }
        
        return error;
    }
    
    public static void main(String[] args){
        System.out.println(ValidityChecker.runChecks("M&C#331000#000133"));
        System.out.println(ValidityChecker.runChecks("M&C#321001#000133"));
        System.out.println(ValidityChecker.runChecks("M&C#a#000133"));
        System.out.println(ValidityChecker.runChecks("M&C#331000#0000133"));
    }
    
}
