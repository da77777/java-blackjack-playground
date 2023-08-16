package nextstep.blackjack.participants.domain;

public class Player extends Participant {

    private String name;
    private int bet;
    private boolean hit = false; //true : 뽑겠다, false : 뽑지 않겠다.

    public String getName() {
        return name;
    }

    public int getBet() {
        return bet;
    }

    public boolean isHit() {
        return hit;
    }

    public Player(String name, int bet) {
        this.name = name;
        this.bet = bet;
    }

    public void modIsHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public String toString() {
        return "Player{" + name +
                " totalNum=" + makeNumMax() +
                ", profit=" + profit +
                ", bet=" + bet +
                ", hit=" + hit +
                ", pCards=" + pCards +
                '}';
    }
}
