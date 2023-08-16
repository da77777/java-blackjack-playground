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
            modProfitByDealerBlackjack(ps);
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

    public void checkUnderNum(Participants ps) {
        pStatus.checkDealerUnderMin(ps.getDealer());
        pStatus.checkPlayerUnderMax(ps.getPlayers());
    }

    public void checkBlackjack(Participants ps) {
        pStatus.checkBlackjack(ps.getDealer());
        //이중 콜론 : 람다식에서 사용 가능. p -> pStatus.checkBlackjack(p) 를 아래와 같이 쓸 수 있다. 인스턴스::메서드
        ps.getPlayers().stream().forEach(pStatus::checkBlackjack);
    }

    public void checkBust(Participants ps) {
        pStatus.checkBust(ps.getDealer());
        ps.getPlayers().stream().forEach(pStatus::checkBust);
    }

    public void modProfitByDealerBlackjack(Participants ps) {
        int betSumNonBlackjack = modProfitByStatus(ps.getPlayers(), p -> !p.isBlackjack(), 1.5, bet -> -bet);
        ps.getDealer().addProfit(betSumNonBlackjack);
    }

    public void modProfitByDealerBust(Participants ps) {
        List<Player> players = ps.getPlayers();
        int betSumBust = modProfitByStatus(players, p -> p.isBust(), 1.0, bet -> -bet);
        int betSumNonBust = modProfitByStatus(players, p -> !p.isBust(), 1.0, bet -> bet);

        ps.getDealer().addProfit( betSumBust - betSumNonBust);
    }

    public void modProfit(Participants ps) {
        Dealer dealer = ps.getDealer();
        List<Player> players = ps.getPlayers();

        //딜러 블랙잭 아니면 -betSumBlackjack
        if(!dealer.isBlackjack()) {
            int betSumBlackjack = modProfitByStatus(players, p -> p.isBlackjack(), 1.5, bet -> bet);
            dealer.addProfit( -betSumBlackjack);
        }

        //bust 된 플레이어
        int betSumBust = modProfitByStatus(players, p -> p.isBust(), 1.0, bet -> -bet);
        //딜러 무조건 +betSum
        dealer.addProfit(betSumBust);

        //블랙잭도, bust 도 아닌 경우
        int dTotalNum = dealer.getTotalNum();
        List<Player> normalPlayers = ps.getPlayers().stream()
                .filter(p -> !p.isBlackjack() && !p.isBust())
                .collect(Collectors.toList());

        int betSumWin = modProfitByStatus(normalPlayers, p -> p.getTotalNum() > dTotalNum, 1.0, bet -> bet);
        int betSumLose = modProfitByStatus(normalPlayers, p -> p.getTotalNum() < dTotalNum, 1.0, bet -> -bet);

        dealer.addProfit( -betSumWin + betSumLose);
    }

    //Predicate <T> : T 타입을 받아서 boolean 을 리턴하는 함수 인터페이스
    //Function<T, R> : T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
    public int modProfitByStatus(List<Player> players, Predicate<Player> status, double multiple, Function<Integer, Integer> modifier) {
        return players.stream()
                .filter(status)
                .map(p -> {
                    int bet = (int) (p.getBet() * multiple);
                    p.addProfit( modifier.apply(bet));
                    return bet;
                })
                .reduce(0, Integer::sum);
    }

}
