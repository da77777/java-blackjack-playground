package nextstep.blackjack.view;

import nextstep.blackjack.participant.Participant;

public class OutputView {

    private static final String NEWLINE = "\n";
    private static final String DEALER = "딜러";

    private OutputView() {}

    public static void drawFirstCards() {

    }

    public static void dealerAddOneCard() {

    }

    public static void firstCards() {

    }

    public static void totalCards() {

    }

    public static void betAmount() {

    }

    public static void totalProfit() {

    }

    public static void state(Participant p) {

    }

    //inputView 용
    public static void inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void inputBetAmount(String name) {
        System.out.println(name + "의 배팅금은?");
    }

    public static void inputOneMoreCard(String name) {
        System.out.println(name + "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }



}
