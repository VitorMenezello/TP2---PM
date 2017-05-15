package player;

import deck.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {
    // Input methods
    public int readCardInput (int size){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        int index;

        do {
            do {
                try {
                    input = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!input.matches("\\d+"));
            index = Integer.parseInt(input);
        } while ((index < 0) || (index > size));
        return index;
    }

    public String readSuitInput (){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        do {
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while ((input != "Paus") && (input != "Espadas") && (input != "Copas") && (input != "Ouros"));
        return input;
    }

    // Choice and printing methods
    @Override
    public void invalidPlay(){
        this.getPrinter().printInvalid();
    }

    @Override
    public void validPlay() throws InterruptedException {
        Thread.sleep(0);
    }

    @Override
    public String chooseSuit(){
        this.getPrinter().printSuitOptions();
        return readSuitInput();
    }

    @Override
    public int chooseMove(Card topCard, boolean draw, boolean activeSeven, int totalSevens, String chosenSuit) {
        int index;

        // Print options
        this.getPrinter().printTopCard(topCard);
        this.getPrinter().printOptions(this.getHand(), draw);

        // Read input from player
        index = readCardInput (getHand().size());

        return index - 1;
    }
}
