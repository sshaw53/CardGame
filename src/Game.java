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
    }

    // METHODS
    public static void printInstructions() {
        System.out.println("Hello! Welcome to the War card game! This game requires two players. You will " +
                            "each be dealt half of a deck, and will be playing unknown cards to see which one " +
                            "is larger. The order in power goes 2 at the least, and Ace at the top. The game " +
                            "will continue until one player has no cards in their hand left. Good luck!");
    }

    public void playGame() {
        // Pick a card from each hand
        Card card1 = player1.getCard();
        Card card2 = player2.getCard();

        while (!(player1.isHandEmpty() || player2.isHandEmpty())) {
            // Compare the hand
            // If player 1's hand is bigger than player 2's, player 1 gets both cards
            if (card1.getPoint() > card2.getPoint()) {
                player1.addCard(card1);
                player1.addCard(card2);
            }
            // Vice versa
            else if (card2.getPoint() > card1.getPoint()) {
                player2.addCard(card1);
                player2.addCard(card2);
            }
            // Tie case: then play another card and winner gets that card + 3 other cards from the other hand
            else {

            }
            // if a player's hand is empty, then the other player wins
        }
    }

//    public int compareCards(Card card1, Card card2) {
//
//    }
}
