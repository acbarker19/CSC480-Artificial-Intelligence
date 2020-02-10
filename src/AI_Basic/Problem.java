/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI_Basic;

import java.util.ArrayList;

/**
 *
 * @author acbar
 */
public class Problem {
    private State initialState, endState;
    
    public Problem(State initialState, State endState){
        this.initialState = initialState;
        this.endState = endState;
    }
    
    public boolean goalTest(State testState){
        if(testState.toString() == endState.toString()){
            return true;
        }else{
            return false;
        }
    }
    
    public State getInitialState(){
        return initialState;
    }
    
    public State getEndState(){
        return endState;
    }
    
    public String toString(){
        return "Initial State: " + getInitialState() + ", End State: " +
                getEndState();
    }
    
    public static void main(String[] args){
        Problem p = new Problem(new State("331000"),
                new State("000133"));
        
        System.out.println("Has reached goal with 000133: " +
                p.goalTest(new State("000133")));
    }
}

class MAndCProblem extends Problem {
    
    public MAndCProblem(State initialState, State endState){
        super(initialState, endState);
    }
    
    private int[] splitState(State state){
        int[] data = new int[state.toString().length()];
        
        for(int i = 0; i < state.toString().length(); i++){
            data[i] = Character.getNumericValue(state.toString().charAt(i));
        }
        
        return data;
    }
    
    private int[] splitAction(Action action){
        int[] data = new int[2];
        
        int actionTypeEnd = action.toString().indexOf("#") + 1;
        String justNumbers = action.toString().substring(actionTypeEnd,
                action.toString().length());
        
        for(int i = 0; i < 2; i++){
            data[i] = Character.getNumericValue(justNumbers.toString().charAt(i));
        }
        
        return data;
    }
    
    public ArrayList<State> getNextActions(State currentState){
        ArrayList<State> possibleActions = new ArrayList<State>();
        final String PREFIX = "row#";
        final int numSpots = 2;
        
        int[] data = splitState(currentState);
        
        int mLeft = data[0];
        int cLeft = data[1];
        int bLeft = data[2];
        int bRight = data[3];
        int mRight = data[4];
        int cRight = data[5];
        
        if(bLeft == 1){
            for(int miss = 0; miss <= mLeft; miss++){
                for(int cann = 0; cann <= cLeft; cann++){
                    if(miss + cann <= numSpots){
                        possibleActions.add(new State(PREFIX + miss + cann));
                    }else{
                        break;
                    }
                }
            }
        }else if(bRight == 1){
            for(int miss = 0; miss <= mRight; miss++){
                for(int cann = 0; cann <= cRight; cann++){
                    if(miss + cann <= numSpots){
                        possibleActions.add(new State(PREFIX + miss + cann));
                    }else{
                        break;
                    }
                }
            }
        }
        
        return possibleActions;
    }
    
    public State getResults(State currentState, Action action){
        int[] stateData = splitState(currentState);
        int[] actionData = splitAction(action);
        
        int mLeft = stateData[0];
        int cLeft = stateData[1];
        int bLeft = stateData[2];
        int bRight = stateData[3];
        int mRight = stateData[4];
        int cRight = stateData[5];
        
        if(bLeft == 1){
            mLeft -= actionData[0];
            mRight += actionData[0];

            cLeft -= actionData[1];
            cRight += actionData[1];
            
            bLeft = 0;
            bRight = 1;
        }else if(bRight == 1){
            mLeft += actionData[0];
            mRight -= actionData[0];

            cLeft += actionData[1];
            cRight -= actionData[1];
            
            bLeft = 1;
            bRight = 0;
        }
        
        State endState = new State("" + mLeft + cLeft + bLeft + bRight +
                mRight + cRight);
        
        return endState;
    }
    
    public static void main(String[] args){
        MAndCProblem mc = new MAndCProblem(new State("331000"),
                new State("000133"));
        
        State mcState = new State("251012");
        System.out.println("Has reached goal with " + mcState.toString() +
                ": " + mc.goalTest(mcState));
        System.out.println("Next Possible Actions: " + mc.getNextActions(mcState));
        
        Action newAction = new Action("row#13");
        System.out.println("Results of action " + newAction + " on state " +
                mcState + ": " + mc.getResults(mcState, newAction));
        mcState = mc.getResults(mcState, newAction);
        
        System.out.println("Results of action " + newAction + " on state " +
                mcState + ": " + mc.getResults(mcState, newAction));
        mcState = mc.getResults(mcState, newAction);
        
        System.out.println("Initial state: " + mc.getInitialState());
        System.out.println("End state: " + mc.getEndState());
        
        System.out.println("mc.toString(): " + mc.toString());
    }
}
