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
public class Agent {
    
    private String problemName;
    private State initialState, endState;
    private Problem problem;
    private Sequence solution;
    
    public Agent(String data){
        String[] text = data.split("#");
        
        switch(text[0]){
            case "M&C":
                problemName = "Missionaries and Cannibals";
                initialState = new State(text[1]);
                endState = new State(text[2]);
                problem = new MAndCProblem(initialState, endState);
                solution = breadthFirstSearch(problem);
                break;
            case "8puzzle":
                problemName = "8 Puzzle";
                initialState = new State(text[1]);
                endState = new State(text[2]);
                break;
            default:
                problemName = null;
                initialState = null;
                endState = null;
                break;
        }
    }
    
    public Sequence getSolution(){
        return solution;
    }
    
    public String getProblemName(){
        return problemName;
    }
    
    public Problem getProblem(){
        return problem;
    }
/*    
    public Sequence getSequence(){
        
    }
    
    public Action simpleProblemSolvingAgent(Percept percept){
        Sequence seq = new Sequence();
        State state = currentWorldState
        Problem problem;
        state = updateState(state, percept)
        if(seq.isEmpty()){
            goal = formulateGoal(state)
            problem = formulateProblem(state, goal);
            seq = search(problem)
            if(seq == failure){
                return null;
            }
        }
        Action action = first(seq)
        seq = rest(seq)
        return action;
    }
    
    private Problem formulateProblem(State state, State goal){
        
    }
*/    
    private Node childNode(Problem problem, Node parent, Action action){
        State state = problem.getResult(parent.getState(), action);
        return new Node(state, parent, action, 1);
    }
    
    private Sequence breadthFirstSearch(Problem problem){
        Node node = new Node(problem.getInitialState(), 0);
        
        if(problem.goalTest(node.getState())){
            return new Sequence(node.getAction());
        }
        
        QueueFIFO frontier = new QueueFIFO();
        frontier.insert(node);

        QueueFIFO explored = new QueueFIFO();
        
        int count = 0;
        
        while(true){
            if(frontier.isEmpty()){
                return null;
            }

            node = (Node)frontier.pop();
            explored.insert(node.getState());
            
            System.out.println("#" + count + ". CURRENT STATE: " + node.getState());
            
            for(Action action : problem.getNextActions(node.getState())){
                Node child = childNode(problem, node, action);
                if(!explored.contains(child.getState()) ||
                        !frontier.contains(child.getState())){
                    System.out.println("\tPOSSIBLE ACTION: " +
                            action.toString() + "   RESULT OF ACTION: " +
                            problem.getResult(node.getState(), action));
                    
                    if(problem.goalTest(child.getState())){
                        System.out.println("--SOLUTION FOUND--");
                        return child.getSolution();
                    }
                    
                    frontier.insert(child);
                }
            }
            
            count++;
        }
    }
    
    public static void main(String[] args){
        Agent agent = new Agent("M&C#331000#000133");
        agent.breadthFirstSearch(agent.getProblem());
    }
    
}
