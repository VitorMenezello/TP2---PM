package game;

import deck.*;

public class Game {
    private CardDeck drawingStack;
    private CardDeck playingStack;
    private Player[] players;

    public Game(int numPlayers) {
        // Initialize drawing stack
        this.drawingStack = new CardDeck(2);
        this.drawingStack.shuffleDeck();

        // Initialize playing stack
        this.playingStack = new CardDeck(0);

        // Initialize array of players
        this.players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.players[i] = new Player();
        }
    }

    /* Play a game of Mau-mau */
    public void play() {
        System.out.println("Method Game.play() not implemented...");
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
}
