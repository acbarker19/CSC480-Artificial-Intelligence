/**
 * Resolution.
 * A class that handles resolutions of clauses and sentences.
 * 
 * @author Alec Barker
 */
package AI_Basic;

import java.util.ArrayList;

public class Resolution {
    public boolean plResolution(Sentence KB, Sentence alpha){
        ArrayList<Sentence> KBAndAlpha = new ArrayList<Sentence>();
        KBAndAlpha.add(KB);
        KBAndAlpha.add(alpha);
        
        ArrayList<Clause> clauses = getAllClauses(KBAndAlpha);
        ArrayList<Clause> newClauses = new ArrayList<Clause>();
        
        while(true){
            for(int i = 0; i < clauses.size() - 1; i++){
                Clause ci = clauses.get(i);
                
                for(int j = i + 1; j < clauses.size(); j++){
                    Clause cj = clauses.get(j);
                    
                    ArrayList<Clause> resolvents = plResolve(ci, cj);
                    
                    if(containsEmptyClause(resolvents)){
                        return true;
                    }
                    
                    newClauses = union(newClauses, resolvents);
                }
                
                if(isSubset(newClauses, clauses)){
                    clauses = union(clauses, newClauses);
                }
            }
        }
    }
    
    public ArrayList<Clause> getAllClauses(ArrayList<Sentence> sentences){
        ArrayList<Clause> clauses = new ArrayList<Clause>();
        
        for(Sentence sentence : sentences){
            clauses.addAll(sentence.getClauses());
        }
        
        return clauses;
    }
    
    public boolean containsEmptyClause(ArrayList<Clause> resolvents){
        for(Clause clause : resolvents){
            if(clause.getLiterals().isEmpty()){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Clause> union(ArrayList<Clause> clauses1, ArrayList<Clause> clauses2){
        ArrayList<Clause> union = new ArrayList<Clause>();
        
        for(Clause clause1 : clauses1){
            boolean clausesMatch = false;
            
            for(Clause clause2 : clauses2){
                if(clause1.toString().equals(clause2.toString()) == false){
                    clausesMatch = true;
                    break;
                }
            }
            
            if(clausesMatch == false){
                union.add(clause1);
            }
        }
        
        return union;
    }
    
    public boolean isSubset(ArrayList<Clause> clauses1, ArrayList<Clause> clauses2){
        for(Clause clause1 : clauses1){
            boolean clausesMatch = false;
            
            for(Clause clause2 : clauses2){
                if(clause1.toString().equals(clause2.toString()) == false){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public ArrayList<Clause> plResolve(Clause ci, Clause cj){
        ArrayList<Clause> clauses = new ArrayList<Clause>();
        
        ArrayList<Literal> ciLiterals = ci.getLiterals();
        ArrayList<Literal> cjLiterals = cj.getLiterals();
        ArrayList<Literal> allLiterals = new ArrayList<Literal>();
        allLiterals.addAll(ciLiterals);
        allLiterals.addAll(cjLiterals);
        System.out.println(allLiterals.toString());
        
        while(true){
            boolean somethingAdded = false;
            
            for(Literal ciLit : ciLiterals){
                boolean litNegated = false;

                for(Literal cjLit : cjLiterals){
                    if(ciLit.toString().equals(cjLit.getNegation().toString())){
                        ArrayList<Literal> negatedLiterals = allLiterals;
                        negatedLiterals.removeIf(n -> (n.toString()
                                .equals(ciLit.toString())) ||
                                n.toString().equals(cjLit.getNegation().toString()));
                        Clause newClause = new Clause("", negatedLiterals);
                        
                        System.out.println(allLiterals.toString() + " " + newClause.toString());
                        
                        if(clauses.size() == 0){
                            clauses.add(newClause);
                            litNegated = true;
                            somethingAdded = false;
                            break;
                        }else{
                            for(Clause clause : clauses){
                                System.out.println(clause.toString() + " " + newClause.toString());
                                if(clause.toString().equals(newClause.toString()) == false){
                                    System.out.println("ADDED " + newClause.toString());
                                    clauses.add(newClause);
                                    litNegated = true;
                                    somethingAdded = false;
                                    break;
                            }
                        }
                        }
                    }
                }
                
                if(litNegated){
                    break;
                }
            }
            
            if(somethingAdded = false){
                break;
            }
        }
        
        return clauses;
    }
    
    public static void main(String args[]){
        Resolution resolution = new Resolution();
        
        Literal lit_P = new Literal("", "P");
        Literal lit_Q = new Literal("", "Q");
        Literal lit_R = new Literal("", "R");
        Literal lit_S = new Literal("", "S");
        Literal lit_X = new Literal("", "X");
        
        ArrayList<Literal> litList = new ArrayList<Literal>();
        litList.add(lit_P);
        litList.add(lit_P.getNegation());
        Clause clause_P_or_NotP = new Clause("", litList);
        ArrayList<Clause> clauseList = new ArrayList<Clause>();
        clauseList.add(clause_P_or_NotP);
        Sentence sentence_P_or_NotP = new Sentence(clauseList);        
        
        ArrayList<Literal> litList2 = new ArrayList<Literal>();
        litList2.add(lit_P);
        Clause clause_P = new Clause("", litList2);
        ArrayList<Clause> clauseList2 = new ArrayList<Clause>();
        clauseList2.add(clause_P);
        Sentence sentence_P = new Sentence(clauseList2);
        
        System.out.println("KB: " + sentence_P_or_NotP.toString());
        System.out.println("alpha: " + sentence_P);
//        System.out.println("resolution sentence: ");
        System.out.println("KB entails alpha: " + resolution.plResolution(
                sentence_P_or_NotP, sentence_P));
    }
}
