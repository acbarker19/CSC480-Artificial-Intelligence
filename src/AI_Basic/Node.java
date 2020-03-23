/**
 * Node.
 * A class that keeps track of information relating to each node of the sequence.
 * 
 * @author Alec Barker
 */
package AI_Basic;

public class Node {
    
    private State state;        // The state to which the node corresponds
    private Node parentNode;    // The node in the search tree that generated this node
    private Action action;      // The action that was applied to the parent to generate this node
    private int pathCost;       // The cost - g(n) - of the path from the initial state to the
                                // node using the parent pointers
    private Sequence sequence;
    
    public Node(State state,
                 Node parentNode,
                 Action action,
                 int pathCost){
        this.state = state;
        this.parentNode = parentNode;
        this.action = action;
        this.pathCost = pathCost;
    }
    
    public Node(State state, int pathCost){
        this.state = state;
        parentNode = null;
        action = null;
        this.pathCost = pathCost;
    }
    
    public void setState(State state){
        this.state = state;
    }
    
    public void setParentNode(Node parentNode){
        this.parentNode = parentNode;
    }
    
    public void setAction(Action action){
        this.action = action;
    }
    
    public void setPathCost(int pathCost){
        this.pathCost = pathCost;
    }
    
    public State getState(){
        return state;
    }
    
    public Node getParentNode(){
        return parentNode;
    }
    
    public Action getAction(){
        return action;
    }
    
    public int getPathCost(){
        return pathCost;
    }
    
    public Sequence getSolution(){
        Sequence solution;
        
        if(parentNode == null){
            solution = new Sequence();
        }else{
            solution = parentNode.getSolution();
        }
        
        solution.add(action);
        
        return solution;
    }
    
    public String toString(){
        return "State: " + state + ", Parent Node: {" + parentNode +
                "}, Action: " + action + ", Path Cost: " + pathCost;
    }
    
    public static void main(String[] args){
        System.out.println("Test main for Node class");
        
        State state = new State("33100");
        Action action = new Action("row#11");
        Node theNode = new Node(state,
                                null,
                                action,
                                1);             // path cost
        
        System.out.println("theNode.toString(): " + theNode.toString());
        Sequence theSequence = new Sequence(theNode.getAction());
        System.out.println("theSequence.toString(): " + theSequence.toString());
        System.out.println("theNode.getSolution(): " + theNode.getSolution());
        
        State stateOfChild = new State("220111");
        Action actionOfChild = new Action("row#11");
        Node theChildNode = new Node(stateOfChild,
                                 theNode,       // parent node
                                 actionOfChild,
                                 1);            // path cost
        
        System.out.println("theChildNode.toString(): " + theChildNode.toString());
        theSequence.add(theChildNode.getAction());
        System.out.println("theSequence.toString(): " + theSequence.toString());
        System.out.println("theChildNode.getSolution(): " + theChildNode.getSolution());
        
        State stateOfChild2 = new State("331000");
        Action actionOfChild2 = new Action("row#11");
        Node theChildNode2 = new Node(stateOfChild2,
                                 theChildNode,       // parent node
                                 actionOfChild2,
                                 1);            // path cost
        
        System.out.println("theChildNode2.toString(): " + theChildNode2.toString());
        theSequence.add(theChildNode2.getAction());
        System.out.println("theSequence.toString(): " + theSequence.toString());
        System.out.println("theChildNode2.getSolution(): " + theChildNode2.getSolution());
    }
    
}
