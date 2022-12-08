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
        for (int i = 0; i < 26; i++) {
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
                            "each be dealt half of a deck, and will be playing unknown cards to see \nwhich one " +
                            "is larger. The order in power goes 2 at the least, and Ace at the top. The game " +
                            "will continue until one player has no cards in their hand left. \nGood luck!");
    }

    public void playGame() {
        Scanner s = new Scanner(System.in);
        while (!(player1.isHandEmpty() || player2.isHandEmpty())) {
            // Pick a card from each hand
            Card card1 = player1.getCard();
            Card card2 = player2.getCard();
            ArrayList<Card> cardPile = new ArrayList<Card>();
            // Add the two new cards to the arraylist of available cards
            cardPile.add(card1);
            cardPile.add(card2);


            System.out.println("Player 1 dealt a: " + card1);
            System.out.println("Player 2 dealt a: " + card2);

            s.nextLine();

            // Tie case - if compareCards() returns 0, draw new cards, compare, and give the tie cards + new cards to winner
            while (compareCards(card1, card2, cardPile) == 1) {
                s.nextLine();
                card1 = player1.getCard();
                card2 = player2.getCard();
                cardPile.add(card1);
                cardPile.add(card2);
                System.out.println("Player 1 dealt a: " + card1);
                System.out.println("Player 2 dealt a: " + card2);
            }
            s.nextLine();
        }
        // If a player's hand is empty, then the other player wins
        if (player1.isHandEmpty()) {
            System.out.println("Player 1 has ran out of cards, Player 2 wins!");
        }
        else {
            System.out.println("Player 2 has ran out of cards, Player 1 wins!");
        }
    }

    public int compareCards(Card card1, Card card2, ArrayList<Card> cardPile) {
        // Compare the hand
        // If player 1's hand is bigger than player 2's, player 1 gets both cards
        // Add all cards FROM arraylist TO player1
        if (card1.getPoint() > card2.getPoint()) {
            for (int i = 0; i < cardPile.size(); i++) {
                player1.addCard(cardPile.get(i));
            }
            System.out.println("Player 1 Wins this turn, the " + card1 + " and the " + card2 + " are added to Player 1's hand.");
            return 0;
        }
        // Vice versa
        // Add all cards FROM arraylist TO player2
        else if (card2.getPoint() > card1.getPoint()) {
            for (int i = 0; i < cardPile.size(); i++) {
                player2.addCard(cardPile.get(i));
            }
            System.out.println("Player 2 Wins this turn, the " + card1 + " and the " + card2 + " are added to Player 2's hand.");
            return 0;
        }
        // Tie case, returns 1
        else {
            // Add these two cards TO the arraylist
            cardPile.add(card1);
            cardPile.add(card1);
            System.out.println("There's a tie! Another card will be dealt to determine who gets the tie cards and the new cards dealt.");
            return 1;
        }
    }
}
