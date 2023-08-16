package nextstep.blackjack;

import nextstep.blackjack.card.domain.TrumpCard;
import nextstep.blackjack.game.Game;
import nextstep.blackjack.participants.ParticipantStatus;
import nextstep.blackjack.participants.PlayerCreator;
import nextstep.blackjack.participants.domain.Dealer;
import nextstep.blackjack.participants.domain.Participants;
import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.PrintView;

public class BlackjackApplication {

    public static void main(String[] args) {
        PlayerCreator playerCreator = new PlayerCreator();
        Game game = new Game(new ParticipantStatus());

        TrumpCard.createTrumpCard();

        Participants ps = new Participants(new Dealer(), playerCreator.makePlayers());
        game.drawFirstCard(ps);

        PrintView.dealTowCards(ps);
        PrintView.firstTurnCards(ps);

        game.playGame(ps);

        PrintView.totalCards(ps);
        PrintView.totalProfit(ps);

        InputView.getInstance().close();
    }
}
