package player;

import deck.Card;

public class AutoPlayer extends  Player{
    public boolean validCard(Card playedCard, Card topCard, boolean activeSeven, int totalSevens, String chosenSuit){
        if ((topCard.getValue() == 7)&&(activeSeven)){
            if (playedCard.getValue() == 7){
                totalSevens++;
                return true;
            }
            else {
                return false;
            }
        }
        else if (topCard.getValue() == 11){
            if (playedCard.getSuit() == chosenSuit){
                return true;
            }
            else if (playedCard.getValue() == 11){
                return true;
            }
            else {
                return false;
            }
        }
        else if (topCard.getValue() == playedCard.getValue()){
            return true;
        }
        else if (topCard.getSuit() == playedCard.getSuit()){
            return true;
        }
        else if (playedCard.getValue() == 11){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void invalidPlay(){
    }

    @Override
    public String chooseSuit(){
        return this.getCard(0).getSuit();
    }

    @Override
    public int chooseMove(Card topCard, boolean draw, boolean activeSeven, int totalSevens, String chosenSuit){
        if (!draw){
            this.getPrinter().printTopCard(topCard);
        }
        for (int i = 0; i < this.getHand().size(); i++) {
            if (validCard(this.getCard(i), topCard, activeSeven, totalSevens, chosenSuit)) {
                return i;
            }
        }
        return -1;
    }
}
