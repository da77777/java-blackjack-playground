package nextstep.blackjack.card.state;

import nextstep.blackjack.participant.PlayingCards;

public abstract class Running extends Started {
    public Running(PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double betAmount) {
        return 0;
    }
}
