/**
 * Problem.
 * A class that sets up generic problem constraints.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public abstract class Problem {
    private State initialState, endState;
    
    public Problem(State initialState, State endState){
        this.initialState = initialState;
        this.endState = endState;
    }
    
    public boolean goalTest(State testState){
            return testState.toString().equals(endState.toString());
    }
    
    public State getInitialState(){
        return initialState;
    }
    
    public State getEndState(){
        return endState;
    }
    
    public static int[] splitState(State state){
        int[] data = new int[state.toString().length()];
        
        for(int i = 0; i < state.toString().length(); i++){
            data[i] = Character.getNumericValue(state.toString().charAt(i));
        }
        
        return data;
    }
    
    public int[] splitAction(Action action){
        int[] data = new int[2];
        
        int actionTypeEnd = action.toString().indexOf("#") + 1;
        String justNumbers = action.toString().substring(actionTypeEnd,
                action.toString().length());
        
        for(int i = 0; i < 2; i++){
            data[i] = Character.getNumericValue(justNumbers.charAt(i));
        }
        
        return data;
    }
    
    public String toString(){
        return "Initial State: " + getInitialState() + ", End State: " +
                getEndState();
    }
    
    public abstract State getResult(State currentState, Action action);
    
    public abstract ArrayList<Action> getNextActions(State currentState);
}
