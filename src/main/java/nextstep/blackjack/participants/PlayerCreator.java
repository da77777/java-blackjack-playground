package nextstep.blackjack.participants;

import nextstep.blackjack.participants.domain.Player;
import nextstep.blackjack.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class PlayerCreator {

    public List<Player> makePlayers() {
        List<Player> players = new ArrayList<>();

        String s = InputView.getInstance().inputPlayer();
        String[] names = s.split(",");

        for (String name : names) {
            int bettingAmount = Integer.parseInt(InputView.getInstance().inputBettingAmount(name));
            players.add(new Player(name, bettingAmount));
        }

        return players;
    }
}
