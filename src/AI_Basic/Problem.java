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
    
    public String toString(){
        return "Initial State: " + getInitialState() + ", End State: " +
                getEndState();
    }
    
    public abstract State getResult(State currentState, Action action);
    
    public abstract ArrayList<Action> getNextActions(State currentState);
}
