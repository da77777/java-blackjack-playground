package nextstep.blackjack.card.state;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.participant.PlayingCards;

public class Hit extends Running {
    public Hit(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);
        if(playingCards.isBust()) {
            return new Bust(playingCards);
        }
        return new Hit(playingCards);
    }

    @Override
    public State stay() {
        return new Stay(playingCards);
    }

    @Override
    public String stateName() {
        return "HIT";
    }
}
