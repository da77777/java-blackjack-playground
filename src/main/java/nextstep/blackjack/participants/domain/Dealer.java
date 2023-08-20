package nextstep.blackjack.participants.domain;

import nextstep.blackjack.card.domain.Card;

public class Dealer extends Participant {

    public String getCardStr() {
        Card card = pCards.get(0);
        return card.getNum() + card.getSuit();
    }
}
