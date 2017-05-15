package player;

import deck.*;
import printer.*;
import java.util.ArrayList;

public abstract class Player {
    private Printer printer;
    private ArrayList<Card> hand;
    private int numCards;

    public Player() {
        this.hand = new ArrayList();
        this.numCards = 0;
        this.printer = new Printer();
    }

    /* Get methods */
    // Get the card at the top of the drawing stack
    public void drawCard(CardDeck drawingStack) {
        Card card = drawingStack.drawCard();
        this.hand.add(card);
        this.numCards++;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getNumCards() {
        return this.numCards;
    }

    public Printer getPrinter(){
        return this.printer;
    }

    public Card getCard(int index){
        return this.hand.get(index);
    }

    // Removes the played card from the hand
    public void playCard(int index){
        this.hand.remove(index);
        this.numCards--;
    }

    /* Reaction to valid or invalid plays methods */
    public abstract void invalidPlay();

    public abstract void validPlay() throws InterruptedException;

    /* Choice methods */
    public abstract String chooseSuit();

    // Choose a card to play according to the top of the playing stack
    public abstract int chooseMove(Card topCard, boolean draw, boolean activeSeven, int totalSevens, String chosenSuit);
}
