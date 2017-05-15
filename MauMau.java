import deck.*;
import game.*;

public class MauMau {

    public static void main(String[] args) throws InterruptedException {
        // Initialize a new game
        int numPlayers = 4;
        Game game = Game.getInstance(numPlayers);

        // Play the game
        game.play();

        //System.out.println("Length: " + game.getPlayers().length);

    }

}
