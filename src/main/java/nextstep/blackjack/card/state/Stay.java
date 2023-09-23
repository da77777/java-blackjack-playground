package nextstep.blackjack.card.state;

import nextstep.blackjack.participant.PlayingCards;

public class Stay extends Finished {
    public Stay(PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public double earningRate() {
        return 1.0;
    }

    @Override
    public String stateName() {
        return "STAY";
    }
}
