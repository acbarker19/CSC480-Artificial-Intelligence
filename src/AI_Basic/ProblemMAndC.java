/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI_Basic;

import java.util.ArrayList;

class ProblemMAndC extends Problem {
    
    public ProblemMAndC(State initialState, State endState){
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
    
    public ArrayList<Action> getNextActions(State currentState){
        ArrayList<Action> possibleActions = new ArrayList<Action>();
        final String PREFIX = "row#";
        final int numSpots = 2;
        
        int[] data = splitState(currentState);
        
        int mLeft = data[0];
        int cLeft = data[1];
        int bLeft = data[2];
        int bRight = data[3];
        int mRight = data[4];
        int cRight = data[5];
        
        ArrayList<Action> temp = new ArrayList<Action>();
        
        if(bLeft == 1){
            for(int miss = 0; miss <= mLeft; miss++){
                for(int cann = 0; cann <= cLeft; cann++){
                    if(miss + cann <= numSpots){
                        temp.add(new Action(PREFIX + miss + cann));
                    }
                }
            }
        }else if(bRight == 1){
            for(int miss = 0; miss <= mRight; miss++){
                for(int cann = 0; cann <= cRight; cann++){
                    if(miss + cann <= numSpots){
                        temp.add(new Action(PREFIX + miss + cann));
                    }
                }
            }
        }
        
        for(int i = 0; i < temp.size(); i++){
            if(!(temp.get(i).toString().equals("row#00") ||
                    getResult(currentState, temp.get(i)).toString().equals("FAILURE"))){
                possibleActions.add(temp.get(i));
            }
        }
        
        return possibleActions;
    }
    
    public State getResult(State currentState, Action action){
        State endState = new State("FAILURE");
        
        if(!currentState.toString().equals("FAILURE")){
            int[] stateData = splitState(currentState);
            int[] actionData = splitAction(action);

            int mLeft = stateData[0];
            int cLeft = stateData[1];
            int bLeft = stateData[2];
            int bRight = stateData[3];
            int mRight = stateData[4];
            int cRight = stateData[5];

            if(actionData[0] + actionData[1] <= 2){
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
            }
            
            String possibleState = "" + mLeft + cLeft + bLeft + bRight +
                    mRight + cRight;
            
            if(mLeft >= cLeft && mRight >= cRight){
                endState = new State(possibleState);
            }else if(mRight == 0 && mLeft >= cLeft){
                endState = new State(possibleState);
            }else if(mLeft == 0 && mRight >= cRight){
                endState = new State(possibleState);
            }
        }
        
        return endState;
    }
    
    public static void main(String[] args){
        State mcState = new State("331000");
        
        ProblemMAndC mc = new ProblemMAndC(mcState,
                new State("000133"));
        
        System.out.println("Has reached goal with " + mcState.toString() +
                ": " + mc.goalTest(mcState));
        
        System.out.println("Has reached goal with 110122: " + 
                mc.goalTest(new State("110122")));
        
        System.out.println("Has reached goal with 000133: " + 
                mc.goalTest(new State("000133")));
        
        System.out.println("Next Possible Actions on State " +
                mc.getInitialState() + ": " + mc.getNextActions(mcState));
        
        System.out.println("Next Possible Actions on State " +
                mc.getEndState() + ": " + mc.getNextActions(mc.getEndState()));
        
        System.out.println("Next Possible Actions on State 111022: " +
                mc.getNextActions(new State("111022")));
        
        Action newAction = new Action("row#10");
        System.out.println("Results of action " + newAction + " on state " +
                mcState + ": " + mc.getResult(mcState, newAction));
        
        newAction.setAction("row#20");
        System.out.println("Results of action " + newAction + " on state " +
                mcState + ": " + mc.getResult(mcState, newAction));
        
        newAction.setAction("row#01");
        System.out.println("Results of action " + newAction + " on state " +
                mcState + ": " + mc.getResult(mcState, newAction));
        
        newAction.setAction("row#02");
        System.out.println("Results of action " + newAction + " on state " +
                mcState + ": " + mc.getResult(mcState, newAction));
        
        newAction.setAction("row#11");
        System.out.println("Results of action " + newAction + " on state " +
                mcState + ": " + mc.getResult(mcState, newAction));
        
        newAction.setAction("row#11");
        System.out.println("Results of action " + newAction + " on state " +
                "111022: " + mc.getResult(new State("111022"), newAction));
        
        System.out.println("Initial state: " + mc.getInitialState());
        System.out.println("End state: " + mc.getEndState());
        
        System.out.println("mc.toString(): " + mc.toString());
    }
}