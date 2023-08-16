package nextstep.blackjack.participants;

import nextstep.blackjack.card.domain.TrumpCard;
import nextstep.blackjack.participants.domain.Dealer;
import nextstep.blackjack.participants.domain.Participant;
import nextstep.blackjack.participants.domain.Player;
import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.PrintView;

import java.util.List;

public class ParticipantStatus {
    private static final int MIN = 16;
    private static final int MAX = 21;

    //블랙잭 ================================
    public void checkBlackjack(Participant p) {
        p.makeNumMax();
        if(p.getTotalNum() == MAX && p.getPCards().size() == 2) {
            p.modIsBlackjack(true);
        }
    }


    //bust ================================
    public void checkBust(Participant p) {
        p.makeNumMax();
        if(p.getTotalNum() > MAX) {
            p.modIsBust(true);
        }
    }

    //16이하(dealer) ================================
    public void checkDealerUnderMin(Dealer dealer) {
        if(isDealerUnderMin(dealer)) {
            makeDealerUpperMin(dealer);
        }
    }

    private boolean isDealerUnderMin(Dealer dealer) {
        return dealer.makeNumMax() <= MIN;
    }

    private void makeDealerUpperMin(Dealer dealer) {
        boolean flag = true;
        while(flag) {
            participantHit(dealer);
            PrintView.dealerOneMoreCard();
            flag = isDealerUnderMin(dealer);
        }
    }

    //21이하(player) ================================
    public void checkPlayerUnderMax(List<Player> players) {
        players.stream()
                .filter(this::isPlayerUnderMax)
                .forEach(this::checkPlayerHit);
    }

    private boolean isPlayerUnderMax(Player player) {
        return player.makeNumMax() < MAX;
    }

    private void checkPlayerHit(Player player) {
        boolean flag = true;
        while(flag) {
            addPlayerCard(player);
            flag = player.isHit();
        }
    }

    private void addPlayerCard(Player player) {
        if(!isPlayerUnderMax(player)) {
            player.modIsHit(false);
            return;
        }

        String answer = InputView.getInstance().inputIsMoreCard(player.getName());
        if(!"y".equals(answer)) {
            player.modIsHit(false);
            return;
        }

        participantHit(player);
    }

    private void participantHit(Participant p) {
        p.addCard(TrumpCard.getInstance().drawCard());
    }



}

