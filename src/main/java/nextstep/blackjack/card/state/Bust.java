package nextstep.blackjack.card.state;

import nextstep.blackjack.participant.PlayingCards;

public class Bust extends Finished {
    public Bust(PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public double earningRate() {
        return -1.0;
    }

    @Override
    public String stateName() {
        return "BUST";
    }
}
