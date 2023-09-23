package nextstep.blackjack.card.state;

import nextstep.blackjack.card.Card;
import nextstep.blackjack.participant.PlayingCards;

public abstract class Finished extends Started {
    public Finished(PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public State draw(Card card) {
        System.out.println(this.getClass().getName() + " 상태이므로 카드를 뽑을 수 없습니다.");
        return this;
    }

    @Override
    public State stay() {
        System.out.println(this.getClass().getName() + " 상태이므로 stay 할 수 없습니다.");
        return this;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double betAmount) {
        return earningRate() * betAmount;
    }

    public abstract double earningRate();

}
