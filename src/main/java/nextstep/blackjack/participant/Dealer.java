package nextstep.blackjack.participant;

public class Dealer extends Participant {
    public Dealer() {
        super.assignName("딜러");
    }

    public boolean isUnderLimit() {
        return getPlayingCards().getScoreSum() <= 16;
    }

}
