package nextstep.blackjack.card.state;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.participant.PlayingCards;

public interface State {

    State firstDraw();

    State draw(Card card);

    State stay();

    boolean isFinished();

    PlayingCards playingCards();

    double profit(double betAmount);

    String stateName();
}
