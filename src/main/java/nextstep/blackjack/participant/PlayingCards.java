package nextstep.blackjack.participant;

import nextstep.blackjack.Result;
import nextstep.blackjack.card.Card;
import nextstep.blackjack.card.state.Hit;
import nextstep.blackjack.card.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//카드랑 상태 상호참조
//참여자 > 상태 > 카드 순서로 포함하도록??
//수익계산은 State 에서 빼자 그게 나을듯
public class PlayingCards {
    private final static int BLACKJACK = 21;
    private final static int BUST_LIMIT = 21;
    private final static int MAKE_ACE_11 = 10;

    private List<Card> playCards = new ArrayList<>();
    private int scoreSum = 0;
    private State state = new Hit(this);

    public State getState() {
        return state;
    }

    public int getScoreSum() {
        return scoreSum;
    }

    public void addFirstCards(Card firstCard, Card secondCard) {
        playCards.add(firstCard);
        playCards.add(secondCard);
    }

    public void add(Card card) {
        playCards.add(card);
    }

    public void firstDraw(Card firstCard, Card secondCard) {
        addFirstCards(firstCard, secondCard);
        this.state = this.state.firstDraw();
    }

    public void draw(Card card) {
        this.state = this.state.draw(card);
    }

    public void stay() {
        this.state = this.state.stay();
    }

    //==== state 상태 결정할 때 사용
    public boolean isBust() {
        return getScore() > BUST_LIMIT;
    }

    public boolean isBlackjack() {
        return (playCards.size() == 2 && getScore() == BLACKJACK);
    }
    //state 상태 결정할 때 사용  ====

    public int getScore() {
        scoreSum = getScoreNonAce();

        if(getAce().isEmpty()) {
            return scoreSum;
        }

        if(scoreSum > 10) {
            scoreSum += getScoreAce();
            return scoreSum;
        }

        scoreSum += getScoreAce() + MAKE_ACE_11;
        return scoreSum;
    }

    private int getScoreNonAce() {
        return playCards.stream()
                .filter(p -> !p.isAce())
                .mapToInt(Card::getScore)
                .sum();
    }

    private int getScoreAce() {
        return getAce().stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private List<Card> getAce() {
        return playCards.stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "PlayingCards{" +
                ", scoreSum=" + scoreSum +
                ", " + playCards +
                '}';
    }
}

