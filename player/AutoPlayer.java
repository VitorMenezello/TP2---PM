package player;

import deck.Card;

public class AutoPlayer extends Player{
    // Primitive AI: AutoPlayer chooses to save J's rather than play them as first option
    // Next step: AutoPlayer saves 7's to use if someone it close to winning
    private int search = 0;
    private boolean playJack = false;

    @Override
    public void invalidPlay(){
        if (this.search != this.getHand().size() - 1){
            this.search++;
        }
        else {
            if (!this.playJack){
                this.playJack = true;
                this.search = 0;
            }
            else {
                this.search = -1;
            }
        }
    }

    @Override
    public void validPlay() throws InterruptedException {
        Thread.sleep(1000);
        this.playJack = false;
        this.search = 0;
    }

    @Override
    public String chooseSuit(){
        return this.getCard(0).getSuit();
    }

    @Override
    public int chooseMove(Card topCard, boolean draw, boolean activeSeven, int totalSevens, String chosenSuit){
        if ((this.search == 0) && (!this.playJack) && (!draw)){
            this.getPrinter().printTopCard(topCard);
        }
        if (!this.playJack){
            if (this.getCard(this.search).getValue() == 11){
                if (this.search != this.getHand().size() - 1){
                    this.search++;
                }
            }
        }
        return this.search;
    }
}
