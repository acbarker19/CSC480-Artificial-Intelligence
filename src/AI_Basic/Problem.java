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
public class Problem {
    // M&C / 8puzzle subclasses
    // constructor parameters are start state and end state
    /* Use 4 points from pg 66-67:
    initialState(state),
    actions(state) -> returns array of possible actions,
    result(state, action) -> returns a state after performing an action,
    goalTest(state) -> returns boolean if it reaches goal
    */
    
    private State initialState, currentState, endState;
    
    public Problem(){
        
    }
    
    public Problem(State initialState, State endState){
        this.initialState = initialState;
        this.endState = endState;
    }
    
    public State[] getActions(State currentState){
        State[] possibleStates = new State[0];
        
        return possibleStates;
    }
    
    public State getResult(State currentState, Action actionTaken){
        State nextState = new State("TEST");
        
        return nextState;
    }
     
    public boolean goalTest(State testState){
        if(testState == endState){
            return true;
        }else{
            return false;
        }
    }
}
