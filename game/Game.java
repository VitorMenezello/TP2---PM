package game;

import deck.*;
import player.*;
import printer.*;

public class Game {
    /* Game attributes */
    private CardDeck drawingStack;
    private CardDeck playingStack;
    private Player[] players;
    private Printer printer;
    private int numPlayers;

    // State
    private int direction;
    private int player;

    // 7
    private boolean activeSeven;
    private int totalSevens;

    // J
    private String chosenSuit;

    // Singleton
    private static Game game;

    // Constructor
    private Game(int numPlayers) {
        // Initialize drawing stack
        this.drawingStack = new CardDeck(2);
        this.drawingStack.shuffleDeck();

        // Initialize playing stack
        this.playingStack = new CardDeck(0);

        // Initialize printer
        this.printer = new Printer();

        // Initialize array of players (1 human and 3 automatic)
        this.players = new Player[numPlayers];
        this.players[0] = new HumanPlayer();
        for (int i = 1; i < numPlayers; i++) {
            this.players[i] = new AutoPlayer();
        }
        this.numPlayers = numPlayers;

        // Default rules
        this.direction = 1;
        this.player = 0;

        // Initialize game flags
        this.activeSeven = false;
        this.totalSevens = 0;
        this.chosenSuit = null;
    }

    public static Game getInstance(int numPlayers) {
        if (game == null) {
            game = new Game(numPlayers);
        }
        return game;
    }

    /* Methods */
    public CardDeck getDrawingStack() {
        return this.drawingStack;
    }

    public CardDeck getPlayingStack() {
        return this.playingStack;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    /* Iterator */
    public void nextPlayer(){
        if ((this.player + direction) == this.numPlayers){
            this.player = 0;
        }
        else if ((this.player + direction) < 0){
            this.player = this.numPlayers - 1;
        }
        else {
            this.player += direction;
        }
    }

    /* Game rules */
    // Distribute a number of cards to all players
    public void distributeCards(int numCards){
        for (int i = 0; i < this.numPlayers; i++){
            for (int j = 0; j < numCards; j++)
                this.players[i].drawCard(this.drawingStack);
        }
    }

    // Shuffle the discarded cards into the drawing stack
    public void reShuffle(){
        // Separate top card
        Card topCard = this.playingStack.drawCard();

        // Get all cards from the playing stack
        Card transferCard;
        int size = this.playingStack.getSize();

        // Transfer to the drawing stack
        for(int i = 0; i < size; i++){
            transferCard = this.playingStack.drawCard();
            this.drawingStack.addCard(transferCard);
        }

        // Return top card to the playing stack
        this.playingStack.addCard(topCard);

        // Shuffle the drawing stack
        this.drawingStack.shuffleDeck();
    }

    // Turn first card and activate its effect
    public void startGame(){
        Card drawnCard = this.drawingStack.drawCard();
        this.playingStack.addCard(drawnCard);
        startCard(drawnCard);
    }

    // Check when the game is over
    public boolean gameOver(){
        for (int i = 0; i < this.numPlayers; i++){
            if (this.players[i].getNumCards() == 0){
                return true;
            }
        }
        return false;
    }

    // Prints who won the game
    public void printWinner(){
        int winnerIndex = 0;
        for (int i = 0; i < this.numPlayers; i++){
            if (this.players[i].getNumCards() == 0){
                winnerIndex = i;
            }
        }
        this.printer.printWinner(winnerIndex);
    }

    /* Special cards */
    // Ace: skips next player's turn
    public void aceCard(){
        nextPlayer();
        this.printer.printAce(this.player, this.players[this.player].getHand().size());
    }

    // Queen: reverses the order of play
    public void queenCard(){
        if (this.direction == 1){
            this.direction = -1;
        }
        else {
            this.direction = 1;
        }
        this.printer.printQueen();
    }

    // Nine: previous player draws one card
    public void nineCard(){
        // Go to previous player
        this.direction = (-1) * this.direction;
        nextPlayer();

        // Previous player draws one card
        drawCard(1);

        // Go back to current player
        this.direction = (-1) * this.direction;
        nextPlayer();
    }

    // Seven: next player draws two cards
    public void sevenCard(){
        this.activeSeven = true;
        this.totalSevens++;
    }

    // The player can play a seven and the next player draws the total of sevens
    public void sevenDraw(){
        drawCard(2 * totalSevens);
        this.activeSeven = false;
        this.totalSevens = 0;
    }

    // Jack: can be played whenever, player chooses suit
    public void jackCard(){
        chooseSuit();
        this.printer.printJack(this.chosenSuit);
    }

    // Checks for special cards
    public void specialCard(Card card){
        // A
        if (card.getValue() == 1){
            aceCard();
        }
        // 7
        else if (card.getValue() == 7){
            sevenCard();
        }
        // 9
        else if (card.getValue() == 9){
            nineCard();
        }
        // J
        else if (card.getValue() == 11){
            jackCard();
        }
        // Q
        else if (card.getValue() == 12){
            queenCard();
        }
    }

    // Checks for special cards on the first card
    public void startCard(Card card){
        // A
        if (card.getValue() == 1){
            this.player = this.numPlayers;
            aceCard();
        }
        // 7
        else if (card.getValue() == 7){
            sevenCard();
        }
        // 9
        else if (card.getValue() == 9){
            nineCard();
        }
        // J
        else if (card.getValue() == 11){
            randomSuit();
            this.printer.printJack(this.chosenSuit);
        }
        // Q
        else if (card.getValue() == 12){
            queenCard();
            this.player = this.numPlayers - 1;
        }
    }

    /* Game actions */
    // Checks if drawing stack is empty
    public void deckEmpty(){
        if (this.drawingStack.getDeck().size() == 0){
            reShuffle();
        }
    }

    // Player draws a number of cards
    public void drawCard(int numCards){
        this.printer.printDraw(this.player, this.players[this.player].getHand().size(), numCards);
        for (int i = 0; i < numCards; i++) {
            this.players[this.player].drawCard(this.drawingStack);
        }
    }

    // Player chooses card to play
    public int chooseCard(boolean draw) throws InterruptedException {
        Card topCard = this.playingStack.getTopCard();
        Card chosenCard;
        int index;
        do {
            index = this.players[this.player].chooseMove(topCard, draw, this.activeSeven, this.totalSevens, this.chosenSuit);

            // If player wants to draw/skip
            if (index == -1){
                break;
            }
            else {
                chosenCard = this.players[this.player].getCard(index);
            }
        } while (!validCard(chosenCard));

        this.players[this.player].validPlay();

        return index;
    }

    // Game checks if chosen card is valid
    public boolean validCard(Card chosenCard){
        Card topCard = this.playingStack.getTopCard();
        if ((topCard.getValue() == 7)&&(this.activeSeven)){
            if (chosenCard.getValue() == 7){
                return true;
            }
            else {
                this.players[this.player].invalidPlay();
                return false;
            }
        }
        else if (topCard.getValue() == 11){
            if (chosenCard.getSuit().matches(this.chosenSuit)){
                return true;
            }
            else if (chosenCard.getValue() == 11){
                return true;
            }
            else {
                this.players[this.player].invalidPlay();
                return false;
            }
        }
        else if (topCard.getValue() == chosenCard.getValue()){
            return true;
        }
        else if (topCard.getSuit().matches(chosenCard.getSuit())){
            return true;
        }
        else if (chosenCard.getValue() == 11){
            return true;
        }
        else {
            this.players[this.player].invalidPlay();
            return false;
        }
    }

    // Player plays chosen card
    public void playCard(int cardIndex){
        Card playedCard = this.players[this.player].getCard(cardIndex);

        // Played card is removed from player's hand
        this.players[this.player].playCard(cardIndex);

        // Printed into the console
        this.printer.printPlay(this.player, this.players[this.player].getHand().size(), playedCard);

        // And added to the stack
        this.playingStack.addCard(playedCard);

        // If player only has one card after playing, print "Mau mau!"
        if (this.players[this.player].getHand().size() == 1){
            this.printer.printMaumau(this.player, this.players[this.player].getHand().size());
        }

        // Check for special cards
        specialCard(playedCard);
    }

    // A random suit is set to start the game when the first card is a Jack
    public void randomSuit(){
        this.chosenSuit = this.playingStack.getTopCard().getSuit();
    }

    // Player chooses suit whenever a Jack is played
    public void chooseSuit(){
        this.chosenSuit = this.players[this.player].chooseSuit();
    }

    // Player drew a card and either doesn't want to play or doesn't have valid cards to do so
    public void passTurn(){
        this.printer.printSkip(this.player, this.players[this.player].getHand().size());
    }

    /* Play a game of Mau-mau */
    public void play() throws InterruptedException {
        // All 4 players receive 7 cards
        distributeCards(7);

        // One card from the drawing stack is opened and placed onto the playing stack
        startGame();

        // Main loop
        int cardIndex;

        while(!gameOver()){
            cardIndex = chooseCard(false);

            // If player chose to draw or doesn't have any cards to play
            if (cardIndex == -1){

                // If the last card played was a seven
                if (this.activeSeven){
                    sevenDraw();
                }
                else {
                    drawCard(1);

                    // Check if deck is empty
                    deckEmpty();

                    // Player can play after drawing one card
                    cardIndex = chooseCard(true);

                    // If the player chose to pass or doesn't have any cards to play
                    if (cardIndex == -1) {
                        passTurn();
                    }
                    // Play card
                    else {
                        playCard(cardIndex);
                    }
                }
            }
            // Play card
            else {
                playCard(cardIndex);
            }

            // Iterator defines next player
            nextPlayer();
        }

        // Loop ended: game is over!
        printWinner();
    }
}
