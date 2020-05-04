/**
 * QueueFIFO.
 * A class that keeps track of the frontier information.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class QueueFIFO {
    
    private ArrayList<Node> list;
    
    public QueueFIFO(){
        list = new ArrayList<Node>();
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public Node pop(){
        if(isEmpty()){
            return null;
        }else{
            Node node = list.get(0);
            list.remove(0);
            return node;
        }
    }
    
    public boolean contains(Object element){
        return list.contains(element);
    }
    
    public void insert(Node node){
        list.add(node);
    }
    
    public ArrayList<Node> getList(){
        return list;
    }
    
    public int size(){
        return list.size();
    }
    
    public Node getNodeWithState(State state){
        System.out.println("Looking for State: " + state);
        for(Node node : list){
            System.out.println("\tLooked at a node with state: " + node.getState());
            if(node.getState().toString().equals(state.toString())){
                return node;
            }
        }
        return null;
    }
    
    public void replaceNodeWithSmallestPathCost(Node child){
        Node currentNode = getNodeWithState(child.getState());
        
        if(currentNode != null){
            if(currentNode.getPathCost() > child.getPathCost()){
                list.remove(currentNode);
                list.add(child);
            }
        }
    }
    
    public static void main(String[] args){
        System.out.println("Test main for QueueFIFO class");
        QueueFIFO theQueue = new QueueFIFO();
        
        System.out.println("Queue starts empty: " + theQueue.isEmpty());
        
        Node node = new Node(new State("331000"), null, new Action("row#11"), 1);
        theQueue.insert(node);
        
        node = new Node(new State("221011"), null, new Action("row#11"), 1);
        theQueue.insert(node);
        
        System.out.println("After inserting nodes, theQueue is empty: " +
                theQueue.isEmpty() + "\r\nThe size of the queue: " +
                theQueue.size() + "\r\nThe queue contains: " + theQueue.getList());
        
        node = theQueue.pop();
        System.out.println("The popped object is: " + node.toString());
        System.out.println("The first node popped from theQueue is: " +
                node.toString() + "\r\nThe size of the queue: " +
                theQueue.size() + "\r\nThe queue contains: " + theQueue.getList());
    }
    
}
