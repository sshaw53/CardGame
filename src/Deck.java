import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] points) {
        cards = new ArrayList<Card>();
        // For every suit
        for (int i = 0; i < suits.length; i++) {
            // For every rank
            for (int j = 0; j < ranks.length; j++) {
                cards.add(new Card(ranks[j], suits[i], points[j]));
            }
        }
        cardsLeft = cards.size();
        shuffle();
    }

    // Checking if the deck has no cards left
    public boolean isEmpty() {
        if (cardsLeft == 0)
            return true;
        return false;
    }

    // Returns  number of cards in the deck left to be dealt
    public int getCardsLeft() {
        return cardsLeft;
    }

    // “Deals” a card by selecting a card from the deck and returning it
    public Card deal() {
        if (isEmpty())
            return null;
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    // Shuffles the deck
    public void shuffle() {
        // Reorder
        for (int i = cards.size() - 1; i >= 0; i--) {
            int switchIdx = (int) (Math.random() * i + 1);
            Card cardCopy = cards.remove(switchIdx);
            cards.add(switchIdx, cards.remove(i));
            cards.add(i, cardCopy);
        }
        // Set cardsLeft back to num of cards in deck
        cardsLeft = cards.size();
    }
}
