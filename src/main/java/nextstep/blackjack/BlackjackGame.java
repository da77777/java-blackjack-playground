package nextstep.blackjack;

import nextstep.blackjack.card.Cards;
import nextstep.blackjack.participant.*;
import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.OutputView;

import java.util.List;

public class BlackjackGame {

    private Cards cards = new Cards();

    public void game() {
        PlayerCreator pc = new PlayerCreator();
        Participants participants = new Participants(new Dealer(), pc.creatPlayers());

        play(participants);
    }

    public void play(Participants ps) {
        drawFirstCards(ps);
        checkHit(ps);


        for (Player player : ps.getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println(ps.getDealer().toString());
    }

    public void drawFirstCards(Participants ps) {
        ps.getDealer().firstDraw(cards.draw(), cards.draw());
        ps.getPlayers().stream().forEach(p -> p.firstDraw(cards.draw(), cards.draw()));
    }

    public void checkHit(Participants ps) {
        playersHit(ps.getPlayers());
        dealerHit(ps.getDealer());
    }

    public void playersHit(List<Player> players) {
        for (Player player : players) {
            playerHitUntilFinished(player);
        }
    }

    public void playerHitUntilFinished(Player player) {
        while(!player.getPlayingCards().getState().isFinished()) {
            OutputView.inputOneMoreCard(player.getName());
            checkPlayerHit(player, InputView.addCard());
        }
    }

    public void checkPlayerHit(Player player, boolean addCard) {
        if(addCard) {
            player.draw(cards.draw());
            return;
        }
        player.stay();
    }

    public void dealerHit(Dealer dealer) {
        while(dealer.isUnderLimit()) {
            OutputView.dealerAddOneCard();
            dealer.draw(cards.draw());
        }
    }

    public void temp() {

    }

}
