/**
 * Agent.
 * A class that is used to solve problems using input states.
 * 
 * @author Alec Barker
 */
package AI_Basic;

public class Agent {
    
    private String problemName;
    private State initialState, endState;
    private Problem problem;
    private Sequence solution;
    private boolean consoleMessagesOn;
    
    public Agent(String data){
        consoleMessagesOn = true;
        
        String[] text = data.split("#");
        switch(text[0]){
            case "M&C":
                problemName = "Missionaries and Cannibals";
                initialState = new State(text[1]);
                endState = new State(text[2]);
                problem = new ProblemMAndC(initialState, endState);
                solution = breadthFirstSearch(problem, "Least Steps");
                break;
            case "8puzzle":
                problemName = "8 Puzzle";
                initialState = new State(text[1]);
                endState = new State(text[2]);
                problem = new ProblemEightPuzzle(initialState, endState);
                solution = breadthFirstSearch(problem, "Least Steps");
                break;
            case "Route":
                problemName = "Route Finder";
                initialState = new State(text[1]);
                endState = new State(text[2]);
                String routeString = text[3];
                problem = new ProblemRouteFinder(initialState, endState,
                        routeString);
                solution = breadthFirstSearch(problem, "Shortest Path");
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
        int pathCost = parent.getPathCost() +
                problem.getPathCost(parent.getState(), action);
        return new Node(state, parent, action, pathCost);
    }
    
    private Sequence breadthFirstSearch(Problem problem, String searchType){
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

            node = frontier.pop();
            explored.insert(node);
            
            if(consoleMessagesOn){
                System.out.println("#" + count + ". CURRENT STATE: " +
                        node.getState());
            }
            
            for(Action action : problem.getNextActions(node.getState())){
                Node child = childNode(problem, node, action);
                
                if(searchType.equals("Shortest Path")){
                    frontier.replaceNodeWithSmallestPathCost(child);
                }
                
                if(!explored.contains(child.getState()) ||
                        !frontier.contains(child.getState())){
                    
                    if(consoleMessagesOn){
                        System.out.println("\tPOSSIBLE ACTION: " +
                                action.toString() + "   RESULT OF ACTION: " +
                                problem.getResult(node.getState(), action));
                    }
                    
                    if(problem.goalTest(child.getState())){
                        if(consoleMessagesOn){
                            System.out.println("--SOLUTION FOUND--");
                        }
                        
                        return child.getSolution();
                    }
                    
                    frontier.insert(child);
                }
            }
            
            count++;
        }
    }
    
    public static void main(String[] args){
        System.out.println("Valid Input");
        Agent agent = new Agent("M&C#331000#000133");
        System.out.println("Solution: " + agent.getSolution().toString());
        
        System.out.println("Invalid Input");
        if(ValidityChecker.runChecks("M&C#331000#000155") != null){
            System.out.println(ValidityChecker.runChecks("M&C#331000#000155"));
        }else{
            agent = new Agent("M&C#331000#000155");
            System.out.println("Solution: " + agent.getSolution().toString());
        }
        
        agent = new Agent("8puzzle#012345678#102345678");
        System.out.println("Solution: " + agent.getSolution().toString());
                
        agent = new Agent("8puzzle#012345678#125348607");
        System.out.println("Solution: " + agent.getSolution().toString());
    }
    
}
