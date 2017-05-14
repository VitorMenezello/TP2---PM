package game;

import com.sun.jmx.snmp.agent.SnmpUserDataFactory;
import deck.*;
import player.*;
import printer.*;

import java.util.ArrayList;

public class Game {
    private CardDeck drawingStack;
    private CardDeck playingStack;
    private Player[] players;
    private Printer printer;
    private int numPlayers;
    private int direction;
    private int player;
    private boolean activeSeven;
    private int totalSevens;
    private String chosenSuit;
    private static Game game;

//    public Game(int numPlayers) {
//        // Initialize drawing stack
//        this.drawingStack = new CardDeck(2);
//        this.drawingStack.shuffleDeck();
//
//        // Initialize playing stack
//        this.playingStack = new CardDeck(0);
//
//        // Initialize array of players (1 human and 3 automatic)
//        this.players = new Player[numPlayers];
//        this.players[0] = new HumanPlayer();
//        for (int i = 1; i < numPlayers; i++) {
//            this.players[i] = new AutoPlayer();
//        }
//    }

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

    public CardDeck getDrawingStack() {
        return this.drawingStack;
    }

    public CardDeck getPlayingStack() {
        return this.playingStack;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    // Flow control
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

    // Game rules
    public void distributeCards(Player[] players, CardDeck deck, int numCards){
        for (int i = 0; i < this.numPlayers; i++){
            for (int j = 0; j < numCards; j++)
                players[i].drawCard(deck);
        }
    }

    public void startGame(){
        Card drawnCard = this.drawingStack.drawCard();
        drawnCard.setOpen(true);
        this.playingStack.addCard(drawnCard);
        startCard(drawnCard);
    }

    public boolean gameOver(){
        for (int i = 0; i < this.numPlayers; i++){
            if (this.players[i].getNumCards() == 0){
                return true;
            }
        }
        return false;
    }

    // Special cards
    public void aceCard(){
        nextPlayer();
        this.printer.printAce(this.player, this.players[this.player].getHand().size());
    }

    public void queenCard(){
        if (this.direction == 1){
            this.direction = -1;
        }
        else {
            this.direction = 1;
        }
        this.printer.printQueen();
    }

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

    public void sevenCard(){
        this.activeSeven = true;
        this.totalSevens++;
        //drawCard(2);
    }

    public void sevenDraw(){
        drawCard(2 * totalSevens);
        this.activeSeven = false;
        this.totalSevens = 0;
    }

    public void jackCard(){
        chooseSuit();
        this.printer.printJack(this.chosenSuit);
    }

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

    public void startCard(Card card){
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
            randomSuit();
            this.printer.printJack(this.chosenSuit);
        }
        // Q
        else if (card.getValue() == 12){
            queenCard();
            this.player = this.numPlayers - 1;
        }
    }

    // Game actions
    public void drawCard(int numCards){
        this.printer.printDraw(this.player, this.players[this.player].getHand().size(), numCards);
        for (int i = 0; i < numCards; i++) {
            this.players[this.player].drawCard(this.drawingStack);
        }
    }

    public int chooseCard(boolean draw){
        Card topCard = this.playingStack.getTopCard();
        return this.players[this.player].chooseMove(topCard, draw, this.activeSeven, this.totalSevens, this.chosenSuit);
    }

    public void playCard(int cardIndex){
        Card playedCard = this.players[this.player].getCard(cardIndex);
        this.players[this.player].playCard(cardIndex);
        this.printer.printPlay(this.player, this.players[this.player].getHand().size(), playedCard);
        this.playingStack.addCard(playedCard);
        if (this.players[this.player].getHand().size() == 1){
            this.printer.printMaumau(this.player, this.players[this.player].getHand().size());
        }
        specialCard(playedCard);
    }

    public void randomSuit(){
        this.chosenSuit = this.playingStack.getTopCard().getSuit();
    }

    public void chooseSuit(){
        this.chosenSuit = this.players[this.player].chooseSuit();
    }

    public void passTurn(){
        this.printer.printSkip(this.player, this.players[this.player].getHand().size());
    }

    /* Play a game of Mau-mau */
    public void play() {
        // All 4 players receive 7 cards
        distributeCards(this.players, this.drawingStack, 7);

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

                    // Player can play after drawing one card
                    cardIndex = chooseCard(true);

                    // If the player chose to pass or doesn't have any cards to play
                    if (cardIndex == -1) {
                        passTurn();
                    }
                    else {
                        playCard(cardIndex);
                    }
                }
            }
            else {
                playCard(cardIndex);
            }

            nextPlayer();
        }

        // To do
        System.out.println("Method Game.play() not implemented...");
    }
}
