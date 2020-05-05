/**
 * Sentence.
 * A class that handles sentence information for logical resolution.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class Sentence {
    ArrayList<Clause> clauses;
    
    public Sentence(ArrayList<Clause> clauses){
        this.clauses = clauses;
    }
    
    public Sentence(){
        clauses = new ArrayList<Clause>();
    }
    
    public ArrayList<Clause> getClauses(){
        return clauses;
    }
    
    public String toString(){
        String sentence = "";
        
        sentence += "( ";
        
        for(int i = 0; i < clauses.size(); i++){
            sentence += clauses.get(i).toString();
            if(i != clauses.size() - 1){
                sentence += " ^ ";
            }
        }
        
        sentence += " )";
        
        return sentence;
    }
    
    public void distributeClauses(ArrayList<Clause> clauses,
            ArrayList<Clause> result, int depth, Clause current){
        if (depth == clauses.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < clauses.get(depth).size(); i++) {
            if(current.size() == clauses.size()){
                current = new Clause();
            }
            
            current.add(clauses.get(depth).get(i));
            distributeClauses(clauses, result, depth + 1, current);
        }
    }
    
    public Sentence getNegation(){
        if(clauses.size() == 1){
            return clauses.get(0).getNegation();
        }else{
            ArrayList<Clause> newClauses = new ArrayList<Clause>();
            distributeClauses(clauses, newClauses, 0, new Clause());
            
            ArrayList<Clause> negatedClauses = new ArrayList<Clause>();
            
            for(Clause clause : newClauses){
                Clause newClause = new Clause();
                
                for(Literal literal : clause.getLiterals()){
                    newClause.add(literal.getNegation());
                }
                
                negatedClauses.add(newClause);
            }
            
            return new Sentence(negatedClauses);
        }
    }
    
    public Sentence combineSentences(ArrayList<Sentence> sentences){
        ArrayList<Clause> clauses = new ArrayList<Clause>();
        
        for(Sentence sentence : sentences){
            for(Clause clause : sentence.getClauses()){
                clauses.add(clause);
            }
        }
        
        return new Sentence(clauses);
    }
    
    public static void main(String[] args){
        Literal lit_P = new Literal("", "P");
        Literal lit_Q = new Literal("", "Q");
        Literal lit_R = new Literal("", "R");
        Literal lit_S = new Literal("", "S");
        Literal lit_A = new Literal("", "A");
        Literal lit_B = new Literal("", "B");
        Literal lit_C = new Literal("", "C");
        
        ArrayList<Literal> litList = new ArrayList<Literal>();
        litList.add(lit_P);
        Clause clause_P = new Clause("", litList);
        
        ArrayList<Literal> litList2 = new ArrayList<Literal>();
        litList2.add(lit_Q);
        Clause clause_Q = new Clause("", litList2);
        
        ArrayList<Literal> litList3 = new ArrayList<Literal>();
        litList3.add(lit_P.getNegation());
        litList3.add(lit_Q);
        Clause clause_NotP_or_Q = new Clause("", litList3);
        
        ArrayList<Literal> litList4 = new ArrayList<Literal>();
        litList4.add(lit_R.getNegation());
        Clause clause_NotR = new Clause("", litList4);
        
        ArrayList<Literal> litList5 = new ArrayList<Literal>();
        litList5.add(lit_R);
        litList5.add(lit_S);
        Clause clause_R_or_S = new Clause("", litList5);
        
        ArrayList<Literal> litList6 = new ArrayList<Literal>();
        litList6.add(lit_A);
        litList6.add(lit_B);
        litList6.add(lit_C.getNegation());
        Clause clause_A_or_B_or_NotC = new Clause("", litList6);
        
        ArrayList<Clause> clauseList = new ArrayList<Clause>();
        clauseList.add(clause_NotP_or_Q);
        Sentence sentence_NotP_or_Q = new Sentence(clauseList);
        System.out.println("sentence_NotP_or_Q: " + sentence_NotP_or_Q.toString());
        System.out.println("sentence_NotP_or_Q negation: " + 
                sentence_NotP_or_Q.getNegation().toString());
        
        clauseList.add(clause_NotR);
        Sentence sentence_NotP_or_Q__and__NotR = new Sentence(clauseList);
        System.out.println("sentence_NotP_or_Q__and__NotR: " +
                sentence_NotP_or_Q__and__NotR.toString());
        System.out.println("sentence_NotP_or_Q__and__NotR negation: " +
                sentence_NotP_or_Q__and__NotR.getNegation().toString());
        
        ArrayList<Clause> clauseList2 = new ArrayList<Clause>();
        clauseList2.add(clause_P);
        clauseList2.add(clause_Q);
        clauseList2.add(clause_NotR);
        Sentence sentence_P__and__Q__and__NotR = new Sentence(clauseList2);
        System.out.println("sentence_P__and__Q__and__NotR: " +
                sentence_P__and__Q__and__NotR.toString());
        System.out.println("sentence_P__and__Q__and__NotR negation: " +
                sentence_P__and__Q__and__NotR.getNegation().toString());
                
        ArrayList<Clause> clauseList3 = new ArrayList<Clause>();
        clauseList3.add(clause_NotP_or_Q);
        clauseList3.add(clause_R_or_S);
        clauseList3.add(clause_A_or_B_or_NotC);
        Sentence sentence_NotP_or_Q__and__R_or_S__and__A_or_B_or_NotC =
                new Sentence(clauseList3);
        System.out.println("sentence_NotP_or_Q__and__R_or_S__and__A_or_B_or_NotC: " +
                sentence_NotP_or_Q__and__R_or_S__and__A_or_B_or_NotC.toString());
        System.out.println("THE NEGATION BELOW IS INCORRECT, BUT I AM NOT SURE HOW TO FIX IT AT THIS TIME");
        System.out.println("sentence_NotP_or_Q__and__R_or_S__and__A_or_B_or_NotC negation: " +
                sentence_NotP_or_Q__and__R_or_S__and__A_or_B_or_NotC.getNegation().toString());
    }
}
