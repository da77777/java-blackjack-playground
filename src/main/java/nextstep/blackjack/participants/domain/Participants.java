package nextstep.blackjack.participants.domain;

import java.util.List;

public class Participants {
    private Dealer dealer;
    private List<Player> players;

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Participants(Dealer dealer, List<Player> players) {
        createDealer(dealer);
        createPlayers(players);
    }

    private void createDealer(Dealer dealer) {
        if(this.dealer == null) {
            this.dealer = dealer;
        }
    }

    private void createPlayers(List<Player> players) {
        if(this.players == null) {
            this.players = players;
        }
    }
}
