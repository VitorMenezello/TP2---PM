package deck;

public class Card {
    String suit;
    String value;
    boolean shuffled;
    boolean open;
    
    public Card() {
        this.suit = "";
        this.value = "";
        this.shuffled = false;
        this.open = false;
    }
    public Card(String su, String val) {
        suit = su;
        value = val;
        this.shuffled = false;
        this.open = false;
    }
    
    void setSuit(String su){
        this.suit = su;
    }
    
    void setValue(String val){
        switch (val) {
            case "1":
                this.value = "A"; break;
            case "11":
                this.value = "J"; break;
            case "12":
                this.value = "Q"; break;
            case "13":
                this.value = "K"; break;
            default:
                this.value = val;
        }
    }

    void setShuffled(boolean sh){
        this.shuffled = sh;
    }
    
    void setOpen(boolean op){
        this.open = op;
    }
    
    public String getSuit(){
        return this.suit;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public boolean isShuffled(){
        return this.shuffled;
    }
    
    public boolean isOpen(){
        return this.open;
    }
    
}
