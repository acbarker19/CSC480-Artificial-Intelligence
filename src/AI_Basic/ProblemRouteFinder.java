/**
 * ProblemRouteFinder.
 * A class that sets up the problem constraints for the route finder.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class ProblemRouteFinder extends Problem{
    
    private ArrayList<Road> map;
    
    public ProblemRouteFinder(State initialState, State endState, String pathString) {
        super(initialState, endState);
        map = new ArrayList<Road>();
        getMap(pathString);
    }
    
    private void getMap(String pathString){
        String[] paths = pathString.split("\n");
        
        for(String path : paths){
            String[] route = path.split(" ");
            map.add(new Road(route[0], Integer.parseInt(route[1]), route[2]));
        }
    }
    
    public String mapToString(){
        String mapString = "Routes:\n";
        
        for(Road road : map){
            mapString += road.toString() + "\n";
        }
        
        return mapString;
    }
    
    @Override
    public int getPathCost(State state, Action action){
        String[] destination = action.toString().split(" ");
        
        for(Road road : map){
            if(road.routeExists(state.toString(), destination[1])){
                return road.getDistance();
            }
        }
        
        return -1;
    }

    @Override
    public State getResult(State currentState, Action action) {
        String[] destination = action.toString().split(" ");
        
        for(Road road : map){
            if(road.routeExists(currentState.toString(), destination[1])){
                return new State(destination[1]);
            }
        }
        
        return null;
    }

    @Override
    public ArrayList<Action> getNextActions(State currentState) {
        ArrayList<Action> possibleActions = new ArrayList<Action>();
        
        for(Road road : map){
            if(road.getCity1().equals(currentState.toString())){
                possibleActions.add(new Action("go " + road.getCity2()));
            }else if(road.getCity2().equals(currentState.toString())){
                 possibleActions.add(new Action("go " + road.getCity1()));
            }
        }
        
        return possibleActions;
    }
    
    public static void main(String[] args){
        ProblemRouteFinder rf = new ProblemRouteFinder(new State("A"),
                new State("Z"), "A 1 B\nB 1 C\nC 1 Z\nC 1 D\nA 100 Z");
        
        System.out.println(rf.toString());
        
        System.out.println("\n" + rf.mapToString());
        
        System.out.println("Path cost from A to B: " +
                rf.getPathCost(new State("A"), new Action("go B")));
        System.out.println("Path cost from B to A: " +
                rf.getPathCost(new State("B"), new Action("go A")));
        System.out.println("Path cost from A to Z: " +
                rf.getPathCost(new State("A"), new Action("go Z")));
        System.out.println("Path cost from A to C: " +
                rf.getPathCost(new State("A"), new Action("go C")));
        System.out.println("Path cost from C to A: " +
                rf.getPathCost(new State("C"), new Action("go A")));
        
        System.out.println("\nResult of going from A to B: " +
                rf.getResult(new State("A"), new Action("go B")));
        System.out.println("Result of going from B to A: " +
                rf.getResult(new State("B"), new Action("go A")));
        System.out.println("Result of going from A to Z: " +
                rf.getResult(new State("A"), new Action("go Z")));
        System.out.println("Result of going from A to C: " +
                rf.getResult(new State("A"), new Action("go C")));
        System.out.println("Result of going from C to A: " +
                rf.getResult(new State("C"), new Action("go A")));
        
        System.out.println("\nPossible actions for A: " +
                rf.getNextActions(new State("A")));
        System.out.println("Possible actions for B: " +
                rf.getNextActions(new State("B")));
        System.out.println("Possible actions for C: " +
                rf.getNextActions(new State("C")));
        System.out.println("Possible actions for D: " +
                rf.getNextActions(new State("D")));
        System.out.println("Possible actions for Z: " +
                rf.getNextActions(new State("Z")));
    }
    
}

class Road {
    String city1, city2;
    int distance;
    
    public Road(String city1, int distance, String city2){
        this.city1 = city1;
        this.distance = distance;
        this.city2 = city2;
    }
    
    public int getDistance(){
        return distance;
    }
    
    public String getCity1(){
        return city1;
    }
    
    public String getCity2(){
        return city2;
    }
    
    public boolean routeExists(String city1, String city2){
        if(city1.equals(this.city1) && city2.equals(this.city2)){
            return true;
        }else if(city1.equals(this.city2) && city2.equals(this.city1)){
            return true;
        }
        
        return false;
    }
    
    public String toString(){
        return "City 1: " + city1 + ", City 2: " + city2 + ", Distance: " +
                distance;
    }
}