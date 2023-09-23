package nextstep.blackjack.participant;

import java.util.List;

public class Participants {
    private Dealer dealer;
    private List<Player> players;


    public Participants( Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
