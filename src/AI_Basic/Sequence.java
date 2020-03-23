/**
 * Sequence.
 * A class that keeps track of the explored information.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class Sequence {
    private ArrayList<Action> actions;
    
    public Sequence(){
        actions = new ArrayList<Action>();
    }
    
    public Sequence(Action action){
        actions = new ArrayList<Action>();
        actions.add(action);
    }
    
    public Sequence(ArrayList<Action> actions){
        this.actions = actions;
    }
    
    public boolean isEmpty(){
        if(actions.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    public void add(Action action){
        actions.add(action);
    }
    
    public Action get(int identifier){
        try{
            return actions.get(identifier);
        }catch(IndexOutOfBoundsException ioobe){
            return null;
        }
    }
    
    public Action getFirst(){
        try{
            return actions.get(0);
        }catch(NullPointerException npe){
            return null;
        }
    }
    
    public void remove(int identifier){
        actions.remove(identifier);
    }
    
    public Sequence getRest(){
        Sequence rest = new Sequence(actions);
        
        rest.remove(0);
        
        return rest;
    }
    
    public ArrayList<Action> getList(){
        return actions;
    }
    
    public int size(){
        return actions.size();
    }
    
    public String toString(){
        String sequence = null;
        
        if(!actions.isEmpty()){
            sequence = "";
            
            for(int i = 0; i < actions.size(); i++){
                if(actions.get(i) != null){
                    sequence += actions.get(i).toString();

                    if(i != actions.size() - 1){
                        sequence += " ";
                    }
                }
            }
        }
        
        return sequence;
    }
    
    public static void main(String[] args){
        System.out.println("Test main for Sequence class");
        
        ArrayList list = new ArrayList();
        Action action = new Action("row#11");
        list.add(action);
        action = new Action("row#12");
        list.add(action);
        action = new Action("row#21");
        list.add(action);
        
        Sequence sequence = new Sequence(list);
        
        System.out.println("Sequence is empty: " + sequence.isEmpty());
        System.out.println("Sequence in order: " + sequence);
        System.out.println("Sequence.toString(): " + sequence.toString());
        System.out.println("First in sequence: " + sequence.getFirst());
        System.out.println("Rest of sequence: " + sequence.getRest());
    }
    
}
