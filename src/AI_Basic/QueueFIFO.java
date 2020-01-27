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
public class QueueFIFO {
    
    private ArrayList<Object> list;
    
    public QueueFIFO(){
        list = new ArrayList<Object>();
    }
    
    public boolean isEmpty(){
        return list.size() == 0;
    }
    
    public Object pop(){
        if(isEmpty()){
            return null;
        }else{
            Object theObject = list.get(0);
            list.remove(0);
            return theObject;
        }
    }
    
    public void insert(Object element){
        list.add(element);
    }
    
    public ArrayList<Object> getList(){
        return list;
    }
    
    public int size(){
        return list.size();
    }
    
    public static void main(String[] args){
        System.out.println("Test main for QueueFIFO class");
        QueueFIFO theQueue = new QueueFIFO();
        
        System.out.println("Queue starts empty: " + theQueue.isEmpty());
        
        Object object = new String("hello");
        theQueue.insert(object);
        
        object = new String("test");
        theQueue.insert(object);
        
        System.out.println("After inserting nodes, theQueue is empty: " +
                theQueue.isEmpty() + "\r\nThe size of the queue: " +
                theQueue.size());
        
        object = theQueue.pop();
        System.out.println("The first node popped from theQueue is: " +
                object.toString() + "\r\nThe size of the queue: " +
                theQueue.size());
    }
    
}
