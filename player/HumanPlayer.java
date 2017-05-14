package player;

import deck.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {
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
            this.getPrinter().printInvalid();
            return false;
        }
    }

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

    @Override
    public void invalidPlay(){
        this.getPrinter().printInvalid();
    }

    @Override
    public String chooseSuit(){
        this.getPrinter().printSuitOptions();
        return readSuitInput();
    }

    @Override
    public int chooseMove(Card topCard, boolean draw, boolean activeSeven, int totalSevens, String chosenSuit) {
        int index;
        do {
            // Print options
            this.getPrinter().printTopCard(topCard);
            this.getPrinter().printOptions(this.getHand(), draw);

            // Read input from player
            index = readCardInput (getHand().size());
            // If player wants to draw/skip
            if (index == 0){
                break;
            }

        } while (!validCard(this.getCard(index - 1), topCard, activeSeven, totalSevens, chosenSuit));


        return index - 1;
    }
}
