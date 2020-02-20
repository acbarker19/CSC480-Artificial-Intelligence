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
        
        Sequence sequence = new Sequence();
        Action action = new Action("row#11");
        
        System.out.println("Sequence is empty: " + sequence.isEmpty());
        System.out.println("Sequence in order: " + sequence.toString());
        
        sequence.add(action);
        System.out.println("Sequence is empty: " + sequence.isEmpty());
        System.out.println("Sequence #10: " + sequence.get(10));
        System.out.println("Sequence #0: " + sequence.get(0));
        
        Action action2 = new Action("row#10");
        sequence.add(action2);
        System.out.println("Sequence in order: " + sequence.toString());
        
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(new Action("row#01"));
        actions.add(new Action("row#10"));
        actions.add(new Action("row#11"));
        actions.add(new Action("row#20"));
        actions.add(new Action("row#02"));
        
        Sequence sequence2 = new Sequence(actions);
        System.out.println("Sequence2 in order: " + sequence2.toString());
        System.out.println("Sequence2 #3: " + sequence2.get(3));
        
    }
    
}
