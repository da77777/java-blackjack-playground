package nextstep.blackjack.card;

public class Card {

    private Suit suit;
    private Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return this.denomination.isAce();
    }

    @Override
    public String toString() {
        return "Card{" +
                "=" + suit +
                ", =" + denomination +
                ", =" + denomination.getScore() +
                '}';
    }
}
