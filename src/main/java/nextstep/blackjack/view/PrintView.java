package nextstep.blackjack.view;

import nextstep.blackjack.participants.domain.Participant;
import nextstep.blackjack.participants.domain.Participants;
import nextstep.blackjack.participants.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PrintView {
    private static final String NEWLINE = "\n";
    private static final String DEALER = "딜러";

    private PrintView() {}

    public static void dealTowCards(Participants ps) {
        String dealerName = DEALER;
        String playersName = ps.getPlayers().stream()
                .map(player -> player.getName())
                .collect(Collectors.joining(", "));
        String sentence = dealerName + "와 "
                + playersName + "에게 2장의 카드를 나누었습니다.";

        System.out.println(sentence);
    }

    public static void dealerOneMoreCard() {
        System.out.println(DEALER + "는 16 이하이므로 한 장의 카드를 더 받았습니다.");
    }

    public static void firstTurnCards(Participants ps) {
        StringBuilder sb = new StringBuilder();

        sb.append(DEALER + " 카드 : " + ps.getDealer().getCardStr() + NEWLINE);
        List<Player> players = ps.getPlayers();
        for (Player player : players) {
            sb.append(player.getName() + "카드 : " + player.getAllCardsStr() + NEWLINE);
        }

        System.out.print(sb);
    }

    public static void totalCards(Participants ps) {
        printBet(ps.getPlayers());
        StringBuilder sb = new StringBuilder();

        sb.append(DEALER + "카드 : " + ps.getDealer().getAllCardsStr() + " [결과: " + ps.getDealer().getTotalNum() + " " + getState(ps.getDealer()) + "] " + NEWLINE);

        List<Player> players = ps.getPlayers();
        for (Player player : players) {
            sb.append(player.getName() + "카드 : " + player.getAllCardsStr() + " [결과: " + player.getTotalNum() + " " + getState(player) + "] " + NEWLINE);
        }

        System.out.print(sb);
    }

    private static void printBet(List<Player> players) {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(player.getName() + " " + player.getBet() + " / ");
        }
        System.out.println(sb);
    }

    private static String getState(Participant p) {
        if(p.getPCards().size() == 2 &&  p.getTotalNum() == 21) {
            return "블랙잭";
        }
        if(p.getTotalNum() > 21) {
            return "bust";
        }
        return "";
    }

    public static void totalProfit(Participants ps) {
        StringBuilder sb = new StringBuilder();

        sb.append("## 최종 수익" + NEWLINE);
        sb.append(DEALER + ": " + ps.getDealer().getProfit() + NEWLINE);

        List<Player> players = ps.getPlayers();
        for (Player player : players) {
            sb.append(player.getName() + ": " + player.getProfit() + NEWLINE);
        }

        System.out.print(sb);
    }


}
