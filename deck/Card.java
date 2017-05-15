package deck;

public class Card {
    String suit;
    int value;
    boolean shuffled;

    public Card() {
        this.suit = "";
        this.value = 0;
        this.shuffled = false;
    }

    public Card(String su, int val) {
        this.suit = su;
        this.value = val;
        this.shuffled = false;
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

    public String getSuit(){
        return this.suit;
    }

    public int getValue(){
        return this.value;
    }

    public boolean isShuffled(){
        return this.shuffled;
    }

    public String toString() {
        return this.value + " " + this.suit;
    }

}
