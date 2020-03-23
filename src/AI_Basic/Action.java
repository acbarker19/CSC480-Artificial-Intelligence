/**
 * Action.
 * A class that stores information about actions.
 * 
 * @author Alec Barker
 */
package AI_Basic;

public class Action {
    
    private String data;
    
    public Action(String data){
        this.data = data;
    }
    
    public void setAction(String data){
        this.data = data;
    }
    
    public String toString(){
        return data;
    }
    
}
