package printer;

import deck.Card;

import java.util.ArrayList;

public class Printer {
    // Print card
    public void printCard(Card card){
        switch (card.getValue()){
            case 1:
                System.out.print("A de " + card.getSuit());
                break;
            case 11:
                System.out.print("J de " + card.getSuit());
                break;
            case 12:
                System.out.print("Q de " + card.getSuit());
                break;
            case 13:
                System.out.print("K de " + card.getSuit());
                break;
            default:
                System.out.print(card.getValue() + " de " + card.getSuit());
                break;
        }
        System.out.println();
    }

    // Print usability
    public void printTopCard(Card topCard){
        System.out.print("Carta do topo: ");
        printCard(topCard);
        System.out.println();
    }

    public void printInvalid(){
        System.out.println("Jogada inválida.");
        System.out.println();
    }

    public void printOptions(ArrayList<Card> hand, boolean draw){
        if (draw){
            System.out.println("Escolha uma carta para jogar, ou digite 0 para pular seu turno:");
            System.out.println("[0]: Pular seu turno");
        }
        else {
            System.out.println("Escolha uma carta para jogar, ou digite 0 para comprar uma carta:");
            System.out.println("[0]: Comprar uma carta");
        }
        for (int i = 0; i < hand.size(); i++) {
            System.out.print("[" + (i + 1) + "]: " );
            printCard(hand.get(i));
        }
        System.out.println();
    }

    public void printSuitOptions(){
        System.out.println("Escolha um naipe digitando um dos nomes a seguir:");
        System.out.println("Paus");
        System.out.println("Espadas");
        System.out.println("Copas");
        System.out.println("Ouros");
        System.out.println();
    }

    // Print game flow
    public void printPlayer(int player, int totalCards){
        if (player == 0){
            System.out.print("Jogador humano [" + totalCards + "] ");
        }
        else {
            System.out.print("Jogador " + player + " [" + totalCards + "] ");
        }
    }

    public void printSkip(int player, int totalCards){
        printPlayer(player, totalCards);
        System.out.println ("passou a vez.");
        System.out.println();
    }

    public void printDraw(int player, int totalCards, int numCards){
        printPlayer(player, totalCards);
        if (numCards > 1){
            System.out.println("comprou " + numCards + " cartas.");
        }
        else {
            System.out.println("comprou " + numCards + " carta.");
        }
        System.out.println();
    }

    public void printPlay(int player, int totalCards, Card playedCard){
        printPlayer(player, totalCards);
        System.out.print("jogou a carta: ");
        printCard(playedCard);
        System.out.println();
    }

    public void printLineBreak(){
        System.out.println("------------------------------------------------------------------");
        System.out.println();
    }

    public void printMaumau(int player, int totalCards){
        printPlayer(player, totalCards);
        System.out.println(": MAU MAU!");
        System.out.println();
    }

    public void printWinner(int player){
        System.out.println("Parabéns Jogador " + player + ", você venceu o jogo!");
        System.out.println();
    }

    // Print special cards
    public void printQueen(){
        System.out.println("Sentido do jogo foi invertido!");
        System.out.println();
    }

    public void printAce(int player, int totalCards){
        printPlayer(player, totalCards);
        System.out.println(" perdeu a vez!");
        System.out.println();
    }

    public void printJack(String suit){
        System.out.println("Naipe escolhido: " + suit);
        System.out.println();
    }
}
