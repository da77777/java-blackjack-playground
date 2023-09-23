package nextstep.blackjack.participant;

public class Player extends Participant {

    private int betAmount;

    public Player(String name, int betAmount) {
        super.assignName(name);
        this.betAmount = betAmount;
    }
}
