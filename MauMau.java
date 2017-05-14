import deck.*;
import game.*;

public class MauMau {

    public static void main(String[] args) {
        /* TESTS */
//        CardDeck deck0 = new CardDeck(0);
//        CardDeck deck1 = new CardDeck(1);
//        CardDeck deck2 = new CardDeck(2);
//
////        deck1.shuffleDeck();
//        Card card = deck1.getDeck().get(1);
//        System.out.println("Card to transfer: " + card.getValue() + " " + card.getSuit());
//        System.out.println();
//        deck1.transferCard(card, deck0);
//
////        for (Card c: deck2.getDeck()) {
////        for (Card c: deck1.getDeck()) {
//        for (Card c: deck0.getDeck()) {
//            System.out.print("Card: ");
//            System.out.println(c.getValue() + " " + c.getSuit());
//        }
//        System.out.println("--------------------------\n");
        /* END TESTS */


        // Initialize a new game
        int numPlayers = 4;
        Game game = Game.getInstance(numPlayers);

        // Play the game
        game.play();

        //System.out.println("Length: " + game.getPlayers().length);

    }

}
