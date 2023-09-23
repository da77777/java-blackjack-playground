package nextstep.blackjack.view;

import nextstep.blackjack.participant.Player;

import java.util.Scanner;

public class InputView {

    private static Scanner sc = new Scanner(System.in);

    public static String[] inputNames() {
        return sc.nextLine().split(",");
    }

    public static int inputBetAmount() {
        return Integer.parseInt(sc.nextLine());
    }

    public static boolean addCard() {
        String addCard = sc.nextLine().toUpperCase();
        return "Y".equals(addCard) ? true : false;

    }

    public static void close() {
        sc.close();
    }

}
