package nextstep.blackjack.view;

import java.util.Scanner;

public class InputView {
    private static InputView inputView = new InputView();

    private InputView() {}

    public static synchronized InputView getInstance() {
        return inputView;
    }

    private final Scanner sc = new Scanner(System.in);

    public String inputAnswer() {
        return sc.nextLine();
    }

    public String inputPlayer() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return inputAnswer();
    }

    public String inputBettingAmount(String name) {
        System.out.println(name + "의 배팅금은?");
        return inputAnswer();
    }

    public String inputIsMoreCard(String name) {
        System.out.println(name + "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return inputAnswer();
    }

    public void close() {
        sc.close();
    }

}
