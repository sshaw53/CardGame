import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    // INSTANCE VARIABLES
    private Player player1;
    private Player player2;
    private Deck deck;
    private String[] ranks = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private String[] suits = new String[]{"Hearts", "Spades", "Diamonds", "Clubs"};
    private int[] points = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    // CONSTRUCTOR
    public Game(String name1, String name2) {
        // get their names, give them each half of the shuffled deck
        deck = new Deck(ranks, suits, points);
        deck.shuffle();

        player1 = new Player(name1);
        player2 = new Player(name2);

        // for half the deck, add a card to the player's hand
        for (int i = 0; i < 52; i++) {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
    }

    // MAIN
    public static void main(String[] args) {
        printInstructions();

        Scanner s = new Scanner(System.in);
        System.out.println("Player 1 input your name: ");
        String name1 = s.nextLine();
        System.out.println("Player 2 input your name: ");
        String name2 = s.nextLine();

        Game game = new Game(name1, name2);

        game.playGame();
    }

    // METHODS
    public static void printInstructions() {
        System.out.println("Hello! Welcome to the War card game! This game requires two players. You will " +
                            "each be dealt half of a deck, and will be playing unknown cards to see which one " +
                            "is larger. The order in power goes 2 at the least, and Ace at the top. The game " +
                            "will continue until one player has no cards in their hand left. Good luck!");
    }

    public void playGame() {
        while (!(player1.isHandEmpty() || player2.isHandEmpty())) {
            // Pick a card from each hand
            Card card1 = player1.getCard();
            Card card2 = player2.getCard();
            // instantiate the arraylist
            // add the two new cards to the arraylist of available cards


            System.out.print("Player 1 dealt a: " + card1);
            System.out.print("Player 2 dealt a: " + card2);

            //compareCards(card1, card2);
            // tie case - if compareCards() returns 0, draw new cards, compare, and give the tie cards + new cards to winner
            while (compareCards(card1, card2) == 0) {
                Card newCard1 = player1.getCard();
                Card newCard2 = player2.getCard();
                compareCards(newCard1, newCard2);
            }
        }
        // If a player's hand is empty, then the other player wins
        if (player1.isHandEmpty()) {
            System.out.println("Player 1 has ran out of cards, Player 2 wins!");
        }
        else {
            System.out.println("Player 2 has ran out of cards, Player 1 wins!");
        }
    }

    public int compareCards(Card card1, Card card2) {
        // Compare the hand
        // If player 1's hand is bigger than player 2's, player 1 gets both cards
        if (card1.getPoint() > card2.getPoint()) {
            player1.addCard(card1);
            player1.addCard(card2);
            // add all cards FROM arraylist TO player1

            System.out.println("Player 1 Wins this turn, the " + card1 + " and the " + card2 + " are added to Player 1's hand.");
            return 1;
        }
        // Vice versa
        else if (card2.getPoint() > card1.getPoint()) {
            player2.addCard(card1);
            player2.addCard(card2);
            // add all cards FROM arraylist TO player2

            System.out.println("Player 2 Wins this turn, the " + card1 + " and the " + card2 + " are added to Player 2's hand.");
            return 2;
        }
        // Tie case, returns 1
        else {
            tieHand.add(card1);
            tieHand.add(card2);
            // add these two cards TO the arraylist

            System.out.println("There's a tie! Another card will be dealt to determine who gets the tie cards and the new cards dealt.");
            return 0;
        }
    }
}
