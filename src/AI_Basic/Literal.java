/**
 * Literal.
 * A class that handles literal information for logical resolution.
 * 
 * @author Alec Barker
 */
package AI_Basic;

public class Literal {
    String preface;
    String name;

    public Literal(String preface, String name){
        this.preface = preface;
        this.name = name;
    }

    public String toString(){
        return preface + name;
    }

    public Literal getNegation(){
        if(preface.equals("~")){
            return new Literal("", name);
        }else{
            return new Literal("~", name);
        }
    }

    public static void main(String[] args){
        Literal lit_P = new Literal("", "P");
        Literal lit_NotP = new Literal("~", "P");
        Literal lit_NotQ = new Literal("~", "Q");

        System.out.println("lit_P: " + lit_P.toString());
        System.out.println("lit_P negation: " + lit_P.getNegation().toString());
        System.out.println("lit_P negation of negation: " +
                lit_P.getNegation().getNegation().toString());
        
        System.out.println("lit_NotP: " + lit_NotP.toString());
        System.out.println("lit_NotP negation: " + lit_NotP.getNegation().toString());
        
        System.out.println("lit_NotQ: " + lit_NotQ.toString());
        System.out.println("lit_NotQ negation: " + lit_NotQ.getNegation().toString());
    }
}
