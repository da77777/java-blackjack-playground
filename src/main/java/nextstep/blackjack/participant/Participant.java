package nextstep.blackjack.participant;

import nextstep.blackjack.card.Card;

public abstract class Participant {

    private PlayingCards playingCards = new PlayingCards();
    //private State state;
    private int profit;
    private String name;

    public PlayingCards getPlayingCards() {
        return playingCards;
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }

    public void assignName(String name) {
        this.name = name;
    }

    public void firstDraw(Card firstCard, Card secondCard) {
        playingCards.firstDraw(firstCard, secondCard);
    }

    public void draw(Card card) {
        playingCards.draw(card);
    }

    public void stay() {
        playingCards.stay();
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name +
                ", state=" + getPlayingCards().getState().stateName() +
                ", profit=" + profit +
                ", =" + playingCards + '\'' +
                '}';
    }
}
