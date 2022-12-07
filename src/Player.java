import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    // Constructors
    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        this.points = 0;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

    // Adds to player's existing points
    public void addPoints(int points) {
        this.points += points;
    }

    // Adds a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getCard() {
        if (hand.isEmpty())
            return null;
        return hand.remove(0);
    }

    // toString method
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
