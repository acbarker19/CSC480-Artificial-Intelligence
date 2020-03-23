/**
 * State.
 * A class that stores information about states.
 * 
 * @author Alec Barker
 */
package AI_Basic;

public class State {
    
    private String data;
    
    public State(String data){
        this.data = data;
    }
    
    public void setState(String data){
        this.data = data;
    }
    
    public String toString(){
        return data;
    }
    
}
