package nextstep.blackjack.game;

import nextstep.blackjack.card.domain.TrumpCard;
import nextstep.blackjack.participants.ParticipantStatus;
import nextstep.blackjack.participants.domain.Dealer;
import nextstep.blackjack.participants.domain.Participants;
import nextstep.blackjack.participants.domain.Player;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {

    private final ParticipantStatus pStatus;
    public Game(ParticipantStatus pStatus) {
        this.pStatus = pStatus;
    }

    //카드 분배
    public void drawFirstCard(Participants ps) {
        ps.getDealer().addFirstCards(TrumpCard.getInstance().drawFirstCard());
        ps.getPlayers().stream().forEach(p -> p.addFirstCards(TrumpCard.getInstance().drawFirstCard()));
    }

    public void playGame(Participants ps) {
        checkBlackjack(ps);
        if(ps.getDealer().isBlackjack()) { //딜러 블랙잭이면 게임 종료
            modProfitByPlayerNonBlackjack(ps);
            return;
        }

        checkUnderNum(ps);

        checkBust(ps);
        if(ps.getDealer().isBust()) { //딜러 bust 이면 게임 종료
            modProfitByDealerBust(ps);
            return;
        }

        modProfit(ps);
    }

    private void checkUnderNum(Participants ps) {
        pStatus.checkDealerUnderMin(ps.getDealer());
        pStatus.checkPlayerUnderMax(ps.getPlayers());
    }

    public void checkBlackjack(Participants ps) {
        pStatus.checkBlackjack(ps.getDealer());
        ps.getPlayers().stream().forEach(pStatus::checkBlackjack);
    }

    public void checkBust(Participants ps) {
        pStatus.checkBust(ps.getDealer());
        ps.getPlayers().stream().forEach(pStatus::checkBust);
    }

    public void modProfit(Participants ps) {
        //딜러 블랙잭 아니면 -betSumBlackjack
        if(!ps.getDealer().isBlackjack()) {
            modProfitByPlayerBlackjack(ps);
        }

        //bust 된 플레이어
        modProfitByPlayerBust(ps);

        //블랙잭도, bust 도 아닌 경우
        modProfitByNum(ps);
    }


    public void modProfitByPlayerNonBlackjack(Participants ps) {
        int betSumNonBlackjack = modProfitByStatus(ps.getPlayers(), p -> !p.isBlackjack(), 1.0, bet -> -bet);
        ps.getDealer().addProfit(betSumNonBlackjack);
    }

    private void modProfitByPlayerBlackjack(Participants ps) {
        int betSumBlackjack = modProfitByStatus(ps.getPlayers(), p -> p.isBlackjack(), 1.5, bet -> bet);
        ps.getDealer().addProfit( -betSumBlackjack);
    }

    public void modProfitByDealerBust(Participants ps) {
        modProfitByPlayerNonBust(ps);
        modProfitByPlayerBust(ps);
    }

    private void modProfitByPlayerNonBust(Participants ps) {
        int betSumNonBust = modProfitByStatus(ps.getPlayers(), p -> !p.isBust(), 1.0, bet -> bet);
        ps.getDealer().addProfit( -betSumNonBust);
    }

    private void modProfitByPlayerBust(Participants ps) {
        int betSumBust = modProfitByStatus(ps.getPlayers(), p -> p.isBust(), 1.0, bet -> -bet);
        ps.getDealer().addProfit( betSumBust);
    }

    public void modProfitByNum(Participants ps) {
        List<Player> normalPlayers = ps.getPlayers().stream()
                .filter(p -> !p.isBlackjack() && !p.isBust())
                .collect(Collectors.toList());

        modProfitByPlayerNumWin(normalPlayers, ps.getDealer());
        modProfitByPlayerNumLose(normalPlayers, ps.getDealer());
    }

    private void modProfitByPlayerNumWin(List<Player> players, Dealer dealer) {
        int betSumWin = modProfitByStatus(players, p -> p.getTotalNum() > dealer.getTotalNum(), 1.0, bet -> bet);
        dealer.addProfit( -betSumWin);
    }

    private void modProfitByPlayerNumLose(List<Player> players, Dealer dealer) {
        int betSumLose = modProfitByStatus(players, p -> p.getTotalNum() < dealer.getTotalNum(), 1.0, bet -> -bet);
        dealer.addProfit( betSumLose);
    }

    //Predicate <T> : T 타입을 받아서 boolean 을 리턴하는 함수 인터페이스
    //Function<T, R> : T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
    private int modProfitByStatus(List<Player> players, Predicate<Player> status, double multiple, Function<Integer, Integer> modifier) {
        return players.stream()
                .filter(status)
                .mapToInt(p -> {
                    int bet = (int) (p.getBet() * multiple);
                    p.addProfit( modifier.apply(bet));
                    return bet;
                })
                .sum();
    }

}
