package game;

import deck.*;
import player.AutoPlayer;
import player.HumanPlayer;
import player.Player;

public class Game {
    private CardDeck drawingStack;
    private CardDeck playingStack;
    private Player[] players;
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

        // Initialize array of players (1 human and 3 automatic)
        this.players = new Player[numPlayers];
        this.players[0] = new HumanPlayer();
        for (int i = 1; i < numPlayers; i++) {
            this.players[i] = new AutoPlayer();
        }
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

    /* Play a game of Mau-mau */
    public void play() {
        System.out.println("Method Game.play() not implemented...");
    }
}
