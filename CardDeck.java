package solitaire2; // Change

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vitor
 */

public class CardDeck {
    private ArrayList deck = new ArrayList();
    
    public CardDeck(ArrayList cardDeck){
        deck = cardDeck;
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
        for(int i = 0; i < suitSize; i++){
            Card card = new Card();
            card.setSuit(suit);
            card.setValue(Integer.toString(i + 1));
            deck.add(card);
        }
    }
    
    public void shuffleDeck(){
        ArrayList shuffledDeck = new ArrayList();
        Random random = new Random();
        for(int i = 0; i < deck.size(); i++) {  
            boolean cardReplaced = false;
            do {
                cardReplaced = false;
                int selectedCardIndex = ((Math.abs(random.nextInt()) % deck.size())); 
                Card deckCard = new Card();
                deckCard = (Card) deck.get(selectedCardIndex);
                if(deckCard.getShuffled() == false) { 
                    deckCard.setShuffled(true);
                    deck.set(selectedCardIndex, deckCard);
                    shuffledDeck.add(i, deck.get(selectedCardIndex));
                    cardReplaced = true;
                }
            }  while(cardReplaced == false);
        }
        deck.clear();
        deck.addAll(shuffledDeck);
        resetDeck();
    }
    
    public void resetDeck() {
        for(int i = 0; i < deck.size(); i++){  
           Card deckCard = new Card();
           deckCard = (Card) deck.get(i); 
           deckCard.setShuffled(false);
           deckCard.setOpen(false);
           deck.set(i, deckCard); 
        }
    }
}