package deck;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
    private ArrayList<Card> deck;
    private int numDecks;

    public CardDeck(int numDecks){
        this.deck = new ArrayList();
        this.numDecks = numDecks;
        initialiseDeck();
    }

    /* Initialising deck */
    private void initialiseDeck(){
        fillSuit("Paus");
        fillSuit("Copas");
        fillSuit("Ouros");
        fillSuit("Espadas");
    }

    private void fillSuit(String suit){
        int suitSize = 13;
        int numDecks = this.numDecks;
        while (numDecks > 0) {
            for(int i = 0; i < suitSize; i++){
                Card card = new Card();
                card.setSuit(suit);
                card.setValue(i + 1);
                this.deck.add(card);
            }
            numDecks--;
        }

    }

    public void shuffleDeck(){
        ArrayList shuffledDeck = new ArrayList();
        Random random = new Random();
        for(int i = 0; i < this.deck.size(); i++) {
            boolean cardReplaced;
            do {
                cardReplaced = false;
                int selectedCardIndex = ((Math.abs(random.nextInt()) % this.deck.size()));
                Card deckCard = this.deck.get(selectedCardIndex);
                if(deckCard.isShuffled() == false) {
                    deckCard.setShuffled(true);
                    this.deck.set(selectedCardIndex, deckCard);
                    shuffledDeck.add(i, this.deck.get(selectedCardIndex));
                    cardReplaced = true;
                }
            }  while(cardReplaced == false);
        }
        this.deck.clear();
        this.deck.addAll(shuffledDeck);
        resetDeck();
    }

    public void resetDeck() {
        for(int i = 0; i < this.deck.size(); i++){
            Card deckCard = this.deck.get(i);
            deckCard.setShuffled(false);
            this.deck.set(i, deckCard);
        }
    }

    /* Deck methods */
    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public int getSize(){
        return this.deck.size();
    }

    /* Card methods */
    // Remove and return card at the top of the deck
    public Card drawCard() {
        Card card = this.deck.get(0);
        this.deck.remove(0);
        return card;
    }

    // Get the card at the top of the deck
    public Card getTopCard() {
        return this.deck.get(0);
    }

    // Add a card to the deck
    public void addCard(Card card) {
        this.deck.add(0, card);
    }
}
