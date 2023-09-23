package nextstep.blackjack.card.state;

import nextstep.blackjack.participant.PlayingCards;

public class Blackjack extends Finished {

    public Blackjack(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public double earningRate() {
        //딜러는?
        return 1.5;
    }

    @Override
    public String stateName() {
        return "BLACKJACK";
    }

}
