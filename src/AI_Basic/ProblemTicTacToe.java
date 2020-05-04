/**
 * ProblemTicTacToe.
 * A class that sets up the problem constraints for the tic tac toe game.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class ProblemTicTacToe extends Problem {

    public ProblemTicTacToe(State initialState) {
        super(initialState, null);
    }
    
    public Action minimax(State currentState){
        ArrayList<Action> actions = getNextActions(currentState);
        
        int v = Integer.MIN_VALUE;
        Action maxAction = null;
        
        for(Action action : actions){
            int u = minValue(getResult(currentState, action));
            if(u > v){
                v = u;
                maxAction = action;
            }
        }
        
        return maxAction;
    }
    
    private int maxValue(State state){
        if(goalTest(state)){
            // computer knows that it will be O
            return utility(state, 'O');
        }
        
        int v = Integer.MIN_VALUE;
        ArrayList<Action> actions = getNextActions(state);
        
        for(Action action : actions){
            v = Integer.max(v, minValue(getResult(state, action)));
        }
        
        return v;
    }
    
    private int minValue(State state){
        if(goalTest(state)){
            // computer knows that it will be O
            return utility(state, 'O');
        }
        
        int v = Integer.MAX_VALUE;
        ArrayList<Action> actions = getNextActions(state);
        
        for(Action action : actions){
            v = Integer.min(v, maxValue(getResult(state, action)));
        }
        
        return v;
    }
    
    private char getNextPlayerCharacter(State currentState){
        // figures out which player went last and adds the opposite character
        // player 1 is always X and goes first
        
        String board = currentState.toString();
        char nextChar;
        
        int xCount = board.length() - board.replace("X", "").length();
        int oCount = board.length() - board.replace("O", "").length();
        
        if(xCount == 0 && oCount == 0){
            nextChar = 'X';
        }else if(xCount == oCount){
            nextChar = 'X';
        }else{
            nextChar = 'O';
        }
        
        return nextChar;
    }
    
    private int utility(State currentState, char playerChar){
        if(goalTest(currentState) == false){
            return Integer.MIN_VALUE;
        }else{
            char[] board = currentState.toString().toCharArray();
            boolean xWin = false;
            boolean oWin = false;
            
            // top row
            if(board[0] != '-' && board[0] == board[1] && board[1] == board[2]){
                if(board[0] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            // middle row
            if(board[3] != '-' && board[3] == board[4] && board[4] == board[5]){
                if(board[3] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            // bottom row
            if(board[6] != '-' && board[6] == board[7] && board[7] == board[8]){
                if(board[6] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            //left column
            if(board[0] != '-' && board[0] == board[3] && board[3] == board[6]){
                if(board[0] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            //middle column
            if(board[1] != '-' && board[1] == board[4] && board[4] == board[7]){
                if(board[1] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            //right column
            if(board[2] != '-' && board[2] == board[5] && board[5] == board[8]){
                if(board[2] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            // top left diagonal
            if(board[0] != '-' && board[0] == board[4] && board[4] == board[8]){
                if(board[0] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            // top right diagonal
            if(board[2] != '-' && board[2] == board[4] && board[4] == board[6]){
                if(board[2] == 'X'){
                    xWin = true;
                }else{
                    oWin = true;
                }
            }
            
            int value;
            
            if(xWin && oWin){
                value = 0;
            }else if((xWin && playerChar == 'X') || (oWin && playerChar == 'O')){
                value = 1;
            }else{
                value = -1;
            }
            
            return value;
        }
    }

    @Override
    public int getPathCost(State state, Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean goalTest(State testState){
        boolean gameOver = false;
        
        char[] board = testState.toString().toCharArray();
        
        // top row
        if(board[0] != '-' && board[0] == board[1] && board[1] == board[2]){
            gameOver = true;
        }
        // middle row
        else if(board[3] != '-' && board[3] == board[4] && board[4] == board[5]){
            gameOver = true;
        }
        // bottom row
        else if(board[6] != '-' && board[6] == board[7] && board[7] == board[8]){
            gameOver = true;
        }
        //left column
        else if(board[0] != '-' && board[0] == board[3] && board[3] == board[6]){
            gameOver = true;
        }
        //middle column
        else if(board[1] != '-' && board[1] == board[4] && board[4] == board[7]){
            gameOver = true;
        }
        //right column
        else if(board[2] != '-' && board[2] == board[5] && board[5] == board[8]){
            gameOver = true;
        }
        // top left diagonal
        else if(board[0] != '-' && board[0] == board[4] && board[4] == board[8]){
            gameOver = true;
        }
        // top right diagonal
        else if(board[2] != '-' && board[2] == board[4] && board[4] == board[6]){
            gameOver = true;
        }
        
        return gameOver;
    }

    @Override
    public State getResult(State currentState, Action action) {
        State state = new State("INVALID ACTION");
        
        int column = Integer.parseInt(action.toString().substring(0, 1));
        int row = Integer.parseInt(action.toString().substring(2, 3));
        
        char[] row1 = currentState.toString().substring(0, 3).toCharArray();
        char[] row2 = currentState.toString().substring(3, 6).toCharArray();
        char[] row3 = currentState.toString().substring(6, 9).toCharArray();
        
        boolean hasChanged = false;
        
        char nextChar = getNextPlayerCharacter(currentState);
        
        if(row == 0 && row1[column] == '-'){
            row1[column] = nextChar;
            hasChanged = true;
        }else if(row == 1 && row2[column] == '-'){
            row2[column] = nextChar;
            hasChanged = true;
        }else if(row == 2 && row3[column] == '-'){
            row3[column] = nextChar;
            hasChanged = true;
        }
        
        if(hasChanged){
            state = new State(new String(row1).concat(new String(row2))
                .concat(new String(row3)));
        }
        
        return state;
    }

    @Override
    public ArrayList<Action> getNextActions(State currentState) {
        ArrayList<Action> possibleActions = new ArrayList<Action>();
        
        char[] board = currentState.toString().toCharArray();
        
        if(board[0] == '-'){
            possibleActions.add(new Action("0 0"));
        }
        if(board[1] == '-'){
            possibleActions.add(new Action("1 0"));
        }
        if(board[2] == '-'){
            possibleActions.add(new Action("2 0"));
        }
        if(board[3] == '-'){
            possibleActions.add(new Action("0 1"));
        }
        if(board[4] == '-'){
            possibleActions.add(new Action("1 1"));
        }
        if(board[5] == '-'){
            possibleActions.add(new Action("2 1"));
        }
        if(board[6] == '-'){
            possibleActions.add(new Action("0 2"));
        }
        if(board[7] == '-'){
            possibleActions.add(new Action("1 2"));
        }
        if(board[8] == '-'){
            possibleActions.add(new Action("2 2"));
        }
        
        return possibleActions;
    }
    
    public static void main(String[] args){
        ProblemTicTacToe pttt = new ProblemTicTacToe(new State("---------"));
        
        State currentState = pttt.getInitialState();
        Action action = new Action("0 2");
        
        System.out.println("If utility value is -2147483648, it means that "
                + "nobody has won the game yet.\n");
        
        System.out.println("Possible actions for " + currentState +
                ": " + pttt.getNextActions(currentState).toString());
        System.out.println("Result of " + action.toString() + " on state " +
                currentState + ": " + pttt.getResult(currentState, action));
        System.out.println("Someone has won: " + pttt.goalTest(currentState));
        System.out.println("Utility value for X: " + pttt.utility(currentState, 'X'));
        System.out.println("Utility value for O: " + pttt.utility(currentState, 'O'));
        System.out.println("Best action for the computer: " + pttt.minimax(currentState));
        System.out.println();
        
        currentState = new State("X--OOX-XO");
        action = new Action("2 0");
        System.out.println("Possible actions for " + currentState +
                ": " + pttt.getNextActions(currentState).toString());
        System.out.println("Result of " + action.toString() + " on state " +
                currentState + ": " + pttt.getResult(currentState, action));
        System.out.println("Someone has won: " + pttt.goalTest(currentState));
        System.out.println("Utility value for X: " + pttt.utility(currentState, 'X'));
        System.out.println("Utility value for O: " + pttt.utility(currentState, 'O'));
        System.out.println("Best action for the computer: " + pttt.minimax(currentState));
        System.out.println();
        
        currentState = new State("---XOXOX-");
        action = new Action("0 1");
        System.out.println("Possible actions for " + currentState +
                ": " + pttt.getNextActions(currentState).toString());
        System.out.println("Result of " + action.toString() + " on state " +
                currentState + ": " + pttt.getResult(currentState, action));
        System.out.println("Someone has won: " + pttt.goalTest(currentState));
        System.out.println("Utility value for X: " + pttt.utility(currentState, 'X'));
        System.out.println("Utility value for O: " + pttt.utility(currentState, 'O'));
        System.out.println("Best action for the computer: " + pttt.minimax(currentState));
        System.out.println();
        
        currentState = new State("X--OXO-OX");
        action = new Action("2 1");
        System.out.println("Possible actions for " + currentState +
                ": " + pttt.getNextActions(currentState).toString());
        System.out.println("Result of " + action.toString() + " on state " +
                currentState + ": " + pttt.getResult(currentState, action));
        System.out.println("Someone has won: " + pttt.goalTest(currentState));
        System.out.println("Utility value for X: " + pttt.utility(currentState, 'X'));
        System.out.println("Utility value for O: " + pttt.utility(currentState, 'O'));
        System.out.println("Best action for the computer: " + pttt.minimax(currentState));
        System.out.println();
        
        currentState = new State("XOXOXOXOX");
        action = new Action("1 1");
        System.out.println("Possible actions for " + currentState +
                ": " + pttt.getNextActions(currentState).toString());
        System.out.println("Result of " + action.toString() + " on state " +
                currentState + ": " + pttt.getResult(currentState, action));
        System.out.println("Someone has won: " + pttt.goalTest(currentState));
        System.out.println("Utility value for X: " + pttt.utility(currentState, 'X'));
        System.out.println("Utility value for O: " + pttt.utility(currentState, 'O'));
        System.out.println("Best action for the computer: " + pttt.minimax(currentState));
        System.out.println();
        
        currentState = new State("XO-XO-XO-");
        action = new Action("2 1");
        System.out.println("Possible actions for " + currentState +
                ": " + pttt.getNextActions(currentState).toString());
        System.out.println("Result of " + action.toString() + " on state " +
                currentState + ": " + pttt.getResult(currentState, action));
        System.out.println("Someone has won: " + pttt.goalTest(currentState));
        System.out.println("Utility value for X: " + pttt.utility(currentState, 'X'));
        System.out.println("Utility value for O: " + pttt.utility(currentState, 'O'));
        System.out.println("Best action for the computer: " + pttt.minimax(currentState));
        System.out.println();

    }
    
}
