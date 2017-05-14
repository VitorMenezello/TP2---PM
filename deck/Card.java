package deck;

public class Card {
    String suit;
    int value;
    boolean shuffled;
    boolean open;

    public Card() {
        this.suit = "";
        this.value = 0;
        this.shuffled = false;
        this.open = false;
    }

    public Card(String su, int val) {
        suit = su;
        value = val;
        this.shuffled = false;
        this.open = false;
    }

    public void setSuit(String su){
        this.suit = su;
    }

    public void setValue(int val){
        this.value = val;
    }

    public void setShuffled(boolean sh){
        this.shuffled = sh;
    }

    public void setOpen(boolean op){
        this.open = op;
    }

    public String getSuit(){
        return this.suit;
    }

    public int getValue(){
        return this.value;
    }

    public boolean isShuffled(){
        return this.shuffled;
    }

    public boolean isOpen(){
        return this.open;
    }

    public String toString() {
        return this.value + " " + this.suit;
    }

}
