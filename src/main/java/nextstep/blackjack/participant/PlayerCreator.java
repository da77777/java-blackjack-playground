package nextstep.blackjack.participant;

import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class PlayerCreator {

    List<Player> players = new ArrayList<>();

    public List<Player> creatPlayers() {
        OutputView.inputPlayerNames();
        String[] names = InputView.inputNames();
        for (String name : names) {
            OutputView.inputBetAmount(name);
            players.add(new Player(name, InputView.inputBetAmount()));
        }

        return players;
    }

}
