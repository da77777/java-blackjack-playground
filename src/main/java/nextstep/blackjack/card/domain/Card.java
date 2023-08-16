package nextstep.blackjack.card.domain;

public class Card {
    private String suit; //다이아, 클로버, 하트, 스페이드
    private int num; //1, 2, 3, ... 11
    private String alphabet; //A, K, Q, J

    public String getSuit() {
        return suit;
    }

    public int getNum() {
        return num;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAceCardNum(int aceCardNum) {
        num = aceCardNum;
    }

    public Card(String suit, int num) {
        this.suit = suit;
        this.num = num;
    }

    public Card(String suit, int num, String alphabet) {
        this.suit = suit;
        this.num = num;
        this.alphabet = alphabet;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", num=" + num +
                ", alphabet='" + alphabet + '\'' +
                '}';
    }
}
