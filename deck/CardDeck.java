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
    
    private void initialiseDeck(){
        fillSuit("Clubs");
        fillSuit("Hearts");
        fillSuit("Diamonds");
        fillSuit("Spades");
    }
    
    private void fillSuit(String suit){
        int suitSize = 13;
        int numDecks_ = this.numDecks;
        while (numDecks_ > 0) {
            for(int i = 0; i < suitSize; i++){
                Card card = new Card();
                card.setSuit(suit);
                card.setValue(Integer.toString(i + 1));
                this.deck.add(card);
            }
            numDecks_--;
        }

    }

    public void shuffleDeck(){
        ArrayList shuffledDeck = new ArrayList();
        Random random = new Random();
        for(int i = 0; i < this.deck.size(); i++) {
            boolean cardReplaced = false;
            do {
                cardReplaced = false;
                int selectedCardIndex = ((Math.abs(random.nextInt()) % this.deck.size()));
                Card deckCard = new Card();
                deckCard = (Card) this.deck.get(selectedCardIndex);
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
           Card deckCard = new Card();
           deckCard = (Card) this.deck.get(i);
           deckCard.setShuffled(false);
           deckCard.setOpen(false);
            this.deck.set(i, deckCard);
        }
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    /* Transfer a card between two decks */
    public void transferCard(Card card, CardDeck receiver) {
        // Remove card from transferring deck
        int index = this.deck.indexOf(card);
        System.out.println("Index = " + index);


        // Add card to receiving deck
        receiver.addCard(card);

        this.deck.remove(index);
    }

    /* Remove and return card at the top of the deck */
    public Card drawCard() {
        Card card = this.deck.get(0);
        this.deck.remove(0);

        return card;
    }

    /* Add a card to the deck */
    public void addCard(Card card) {
        this.deck.add(0, card);
    }

}