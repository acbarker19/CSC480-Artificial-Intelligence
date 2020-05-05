/**
 * Clause.
 * A class that handles clause information for logical resolution.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class Clause {
    String preface;
    ArrayList<Literal> literals;
    
    public Clause(String preface, ArrayList<Literal> literals){
        this.preface = preface;
        this.literals = literals;
    }
    
    public Clause(){
        preface = "";
        literals = new ArrayList<Literal>();
    }
    
    public int size(){
        return literals.size();
    }
    
    public Literal get(int i){
        return literals.get(i);
    }
    
    public void add(Literal literal){
        literals.add(literal);
    }
    
    public String toString(){
        String clause = "";
        
        if(this.preface.equals("~")){
            clause += "~";
        }
        
        clause += "( ";
        
        for(int i = 0; i < literals.size(); i++){
            clause += literals.get(i).toString();
            if(i != literals.size() - 1){
                clause += " v ";
            }
        }
        
        clause += " )";
        
        return clause;
    }
    
    public Sentence getNegation(){
        if(preface.equals("~")){
            ArrayList<Clause> list = new ArrayList<Clause>();
            list.add(new Clause("", this.literals));
            return new Sentence(list);
        }else{
            ArrayList<Clause> list = new ArrayList<Clause>();
        
            for(Literal literal : literals){
                ArrayList<Literal> list2 = new ArrayList<Literal>();
                list2.add(literal.getNegation());
                list.add(new Clause("", list2));
            }
            
            return new Sentence(list);
        }
    }
    
    public ArrayList<Literal> getLiterals(){
        return literals;
    }
    
    public static void main(String[] args){
        Literal lit_R = new Literal("", "R");
        Literal lit_NotP = new Literal("~", "P");
        Literal lit_NotQ = new Literal("~", "Q");
        
        ArrayList<Literal> list = new ArrayList<Literal>();
        
        list.add(lit_R);
        Clause clause_R = new Clause("", list);
        System.out.println("clause_R: " + clause_R.toString());
        System.out.println("clause_R negation: " + clause_R.getNegation().toString());
        
        Clause not_clause_R = new Clause("~", list);
        System.out.println("not_clause_R: " + not_clause_R.toString());
        System.out.println("not_clause_R negation: " + not_clause_R.getNegation().toString());
        
        list.add(lit_NotP);
        Clause clause_R_or_NotP = new Clause("", list);
        System.out.println("clause_R_or_NotP: " + clause_R_or_NotP.toString());
        System.out.println("clause_R_or_NotP negation: " + clause_R_or_NotP.getNegation().toString());
        
        list.add(lit_NotQ);
        Clause clause_R_or_NotP_or_NotQ = new Clause("", list);
        System.out.println("clause_R_or_NotP_or_NotQ: " + clause_R_or_NotP_or_NotQ.toString());
        System.out.println("clause_R_or_NotP_or_NotQ negation: " + clause_R_or_NotP_or_NotQ.getNegation().toString());
    }
}
