package nextstep.blackjack.card.state;

import nextstep.blackjack.participant.PlayingCards;

public abstract class Started implements State {
    protected PlayingCards playingCards;

    public Started(PlayingCards playingCards) {
        this.playingCards = playingCards;
    }

    public PlayingCards playingCards() {
        return playingCards;
    }

    @Override
    public State firstDraw() {
        if(playingCards.isBlackjack()) {
            return new Blackjack(playingCards);
        }
        return new Hit(playingCards);
    }
}
