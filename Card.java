package solitaire2; // Change

/**
 *
 * @author Vitor
 */
public class Card {
    String suit = "";
    String value = "";
    boolean shuffled = false;
    boolean open = false;
    
    public Card() {
        //empty constructor
    }
    public Card(String su, String val) {
        suit = su;
        value = val;
    }
    
    void setSuit(String su){
        suit = su;
    }
    
    void setValue(String val){
        value = val;
    }

    void setShuffled(boolean sh){
        shuffled = sh;
    }
    
    void setOpen(boolean op){
        open = op;
    }
    
    String getSuit(){
        return suit;
    }
    
    String getValue(){
        return value;
    }
    
    boolean isShuffled(){
        return shuffled;
    }
    
    boolean isOpen(){
        return open;
    }
    
}
