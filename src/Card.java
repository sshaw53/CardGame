public class Card {
    private String rank;
    private String suit;
    private int point;

    // Constructor
    public Card (String rank, String suit, int point){
        this.rank = rank;
        this.suit = suit;
        this.point = point;
    }

    // Getters and setters
    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getPoint() {
        return point;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    // toString method
    public String toString() {
        return rank + " of " + suit;
    }
}
