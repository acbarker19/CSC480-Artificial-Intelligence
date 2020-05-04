/**
 * ProblemEightPuzzle.
 * A class that sets up the problem constraints for the 8 puzzle.
 * 
 * @author Alec Barker
 */
package AI_Basic;
import java.util.ArrayList;

public class ProblemEightPuzzle extends Problem{

    public ProblemEightPuzzle(State initialState, State endState) {
        super(initialState, endState);
    }
    
    private int[][] convertStateToTable(State state){
        int[] data = splitState(state);
        
        int[][] table = new int[3][3];
        int i = 0;
        
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                table[x][y] = data[i];
                i++;
            } 
        }
        
        return table;
    }
    
    private State convertTableToState(int[][] table){
        String stateString = "";
        
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                stateString += table[x][y];
            } 
        }
        
        return new State(stateString);
    }
    
    private int[] getBlankTilePositionFromTable(int[][] table){
        int[] position = new int[2];
        position[0] = -1;
        position[1] = -1;
        
        boolean blankFound = false;
        
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                if(table[x][y] == 0){
                    position[0] = x;
                    position[1] = y;
                    blankFound = true;
                    break;
                }
            }
            
            if(blankFound){
                break;
            }
        }
        
        return position;
    }
    
    private int[] getBlankTilePositionFromState(State state){
        int[][] table = convertStateToTable(state);
        
        return getBlankTilePositionFromTable(table);
    }
    
    @Override
    public State getResult(State currentState, Action action) {
        if(action == null || action.toString() == null){
            return currentState;
        }
        
        State endState = new State("FAILURE");
        
        if(!currentState.toString().equals("FAILURE")){
            int[][] table = convertStateToTable(currentState);
            
            int[] positionOfBlank = getBlankTilePositionFromTable(table);
            int blankX = positionOfBlank[0];
            int blankY = positionOfBlank[1];

            if(action.toString().equals("right") && blankX > 0){
                table[blankX][blankY] = table[blankX - 1][blankY];
                table[blankX - 1][blankY] = 0;
                endState = convertTableToState(table);
            }else if(action.toString().equals("left")){
                table[blankX][blankY] = table[blankX + 1][blankY];
                table[blankX + 1][blankY] = 0;
                endState = convertTableToState(table);
            }else if(action.toString().equals("down") && blankY > 0){
                table[blankX][blankY] = table[blankX][blankY - 1];
                table[blankX][blankY - 1] = 0;
                endState = convertTableToState(table);
            }else if(action.toString().equals("up") && blankY < 2){
                table[blankX][blankY] = table[blankX][blankY + 1];
                table[blankX][blankY + 1] = 0;
                endState = convertTableToState(table);
            }
        }
        
        return endState;
    }

    @Override
    public ArrayList<Action> getNextActions(State currentState) {
        ArrayList<Action> possibleActions = new ArrayList<Action>();
        
        int[] positionOfBlank = getBlankTilePositionFromState(currentState);
        int blankX = positionOfBlank[0];
        int blankY = positionOfBlank[1];
        
        if(blankX != -1 && blankY != -1){
            if(blankX > 0){
                possibleActions.add(new Action("right"));
            }
            if(blankX < 2){
                possibleActions.add(new Action("left"));
            }
            if(blankY > 0){
                possibleActions.add(new Action("down"));
            }
            if(blankY < 2){
                possibleActions.add(new Action("up"));
            }
        }
        
        return possibleActions;
    }
    
    @Override
    public int getPathCost(State state, Action action) {
        return 1;
    }
    
    public static void main(String[] args){
        State eightPuzzleState = new State("103425678");
        
        ProblemEightPuzzle eightPuzzle = new ProblemEightPuzzle(eightPuzzleState,
                new State("012345678"));
        
        System.out.println("Has reached goal with " + eightPuzzleState.toString() +
                ": " + eightPuzzle.goalTest(eightPuzzleState));
        
        System.out.println("Has reached goal with 150273864: " + 
                eightPuzzle.goalTest(new State("150273864")));
        
        System.out.println("Has reached goal with 012345678: " + 
                eightPuzzle.goalTest(new State("012345678")));
        
        System.out.println("Next Possible Actions on State " +
                eightPuzzle.getInitialState() + ": " + eightPuzzle.getNextActions(eightPuzzleState));
        
        System.out.println("Next Possible Actions on State " +
                eightPuzzle.getEndState() + ": " + eightPuzzle.getNextActions(eightPuzzle.getEndState()));
        
        System.out.println("Next Possible Actions on State 987654321: " +
                eightPuzzle.getNextActions(new State("987654321")));
        
        Action newAction = new Action("up");
        System.out.println("Results of action " + newAction + " on state " +
                eightPuzzleState + ": " + eightPuzzle.getResult(eightPuzzleState, newAction));
        
        newAction.setAction("down");
        System.out.println("Results of action " + newAction + " on state " +
                eightPuzzleState + ": " + eightPuzzle.getResult(eightPuzzleState, newAction));
        
        newAction.setAction("left");
        System.out.println("Results of action " + newAction + " on state " +
                eightPuzzleState + ": " + eightPuzzle.getResult(eightPuzzleState, newAction));
        
        newAction.setAction("right");
        System.out.println("Results of action " + newAction + " on state " +
                eightPuzzleState + ": " + eightPuzzle.getResult(eightPuzzleState, newAction));
        
        System.out.println("Initial state: " + eightPuzzle.getInitialState());
        System.out.println("End state: " + eightPuzzle.getEndState());
        
        System.out.println("eightPuzzle.toString(): " + eightPuzzle.toString());
    }
 
}
