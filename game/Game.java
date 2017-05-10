package game;

import com.sun.jmx.snmp.agent.SnmpUserDataFactory;
import deck.*;
import player.AutoPlayer;
import player.HumanPlayer;
import player.Player;

import java.util.ArrayList;

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
        // All players receive 7 cards
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 7; j++)
                this.players[i].drawCard(drawingStack);
        }

        // Printing hands
        ArrayList<Card> hand;
        for (int i = 0; i < 4; i++) {
            System.out.println("Jogador " + i + ":");
            hand = this.players[i].getHand();
            System.out.println(hand);
            System.out.println();
        }
        // One card from the drawing stack is opened and placed onto the playing stack
        Card drawnCard = this.drawingStack.drawCard();
        drawnCard.setOpen(true);
        this.playingStack.addCard(drawnCard);

        // To do
        System.out.println("Method Game.play() not implemented...");
    }
}
