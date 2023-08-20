package blackjack.game;

import nextstep.blackjack.card.AlphabetEnum;
import nextstep.blackjack.card.SuitEnum;
import nextstep.blackjack.card.domain.Card;
import nextstep.blackjack.game.Game;
import nextstep.blackjack.participants.ParticipantStatus;
import nextstep.blackjack.participants.domain.Dealer;
import nextstep.blackjack.participants.domain.Participants;
import nextstep.blackjack.participants.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTestBlackjack {

    private static final int P0_BET = 100;
    private static final int P1_BET = 200;
    private static final int P2_BET = 300;
    private static final int P3_BET = 400;
    private static final int P4_BET = 500;
    private static final int ZERO = 0;
    private static final int A_NUM = 1;

    Game game = new Game(new ParticipantStatus());
    Participants ps = null;

    @BeforeEach
    public void gameReady() {
        List<Player> players = new ArrayList<>();
        players.addAll(Arrays.asList(
                new Player("A", P0_BET),
                new Player("B", P1_BET),
                new Player("C", P2_BET),
                new Player("D", P3_BET),
                new Player("E", P4_BET)));

        ps = new Participants(new Dealer(), players);

    }

    //블랙잭 ================================
    @DisplayName("딜러만 블랙잭")
    @Test
    void blackjackD() {
        drawCard_blackjackD();

        game.checkBlackjack(ps);
        if(ps.getDealer().isBlackjack()) { //딜러 블랙잭이면 게임 종료
            game.modProfitByPlayerNonBlackjack(ps);
        }

        assertThat(ps.getDealer().getProfit()).isEqualTo(P0_BET + P1_BET + P2_BET + P3_BET + P4_BET);
        assertThat(ps.getPlayers().get(0).getProfit()).isEqualTo( -P0_BET);
        assertThat(ps.getPlayers().get(1).getProfit()).isEqualTo( -P1_BET);
        assertThat(ps.getPlayers().get(2).getProfit()).isEqualTo( -P2_BET);
        assertThat(ps.getPlayers().get(3).getProfit()).isEqualTo( -P3_BET);
        assertThat(ps.getPlayers().get(4).getProfit()).isEqualTo( -P4_BET);
    }

    void drawCard_blackjackD() {
        ps.getDealer().addFirstCards(drawCards(SuitEnum.DIAMOND, 10, 1));

        ps.getPlayers().get(0).addFirstCards(drawCards(SuitEnum.SPADE, 10, 2)); //12
        ps.getPlayers().get(1).addFirstCards(drawCards(SuitEnum.SPADE, 9, 8)); //17
        ps.getPlayers().get(2).addFirstCards(drawCards(SuitEnum.SPADE, 7, 5)); //12
        ps.getPlayers().get(3).addFirstCards(drawCards(SuitEnum.SPADE, 6, 4)); //10
        ps.getPlayers().get(4).addFirstCards(Arrays.asList(
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.K.getAlphabet()),
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.J.getAlphabet()))); //20
    }

    @DisplayName("딜러랑 플레이어 블랙잭")
    @Test
    void blackjackDP() {
        drawCard_blackjackDP();

        game.checkBlackjack(ps);
        if(ps.getDealer().isBlackjack()) {
            game.modProfitByPlayerNonBlackjack(ps);
        }

        assertThat(ps.getDealer().getProfit()).isEqualTo(P1_BET + P2_BET + P3_BET + P4_BET);
        assertThat(ps.getPlayers().get(0).getProfit()).isEqualTo( ZERO);
        assertThat(ps.getPlayers().get(1).getProfit()).isEqualTo( -P1_BET);
        assertThat(ps.getPlayers().get(2).getProfit()).isEqualTo( -P2_BET);
        assertThat(ps.getPlayers().get(3).getProfit()).isEqualTo( -P3_BET);
        assertThat(ps.getPlayers().get(4).getProfit()).isEqualTo( -P4_BET);
    }

    void drawCard_blackjackDP() {
        ps.getDealer().addFirstCards(drawCards(SuitEnum.DIAMOND, 10, 1)); //21

        ps.getPlayers().get(0).addFirstCards(drawCards(SuitEnum.SPADE, 10, 1)); //21
        ps.getPlayers().get(1).addFirstCards(drawCards(SuitEnum.SPADE, 9, 8)); //17
        ps.getPlayers().get(2).addFirstCards(drawCards(SuitEnum.SPADE, 7, 5)); //12
        ps.getPlayers().get(3).addFirstCards(drawCards(SuitEnum.SPADE, 6, 4)); //10
        ps.getPlayers().get(4).addFirstCards(Arrays.asList(
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.K.getAlphabet()),
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.J.getAlphabet()))); //20
    }

    @DisplayName("플레이어만 블랙잭")
    @Test
    void blackjackP() {
        drawCard_blackjackP();

        game.checkBlackjack(ps);
        game.modProfit(ps);

        assertThat(ps.getDealer().getProfit()).isEqualTo( -(multiplyOPF(P0_BET) + P1_BET + P4_BET) + P3_BET);
        assertThat(ps.getPlayers().get(0).getProfit()).isEqualTo( multiplyOPF(P0_BET));
        assertThat(ps.getPlayers().get(1).getProfit()).isEqualTo( P1_BET);
        assertThat(ps.getPlayers().get(2).getProfit()).isEqualTo( ZERO);
        assertThat(ps.getPlayers().get(3).getProfit()).isEqualTo( -P3_BET);
        assertThat(ps.getPlayers().get(4).getProfit()).isEqualTo( P4_BET);
    }

    void drawCard_blackjackP() {
        ps.getDealer().addFirstCards(drawCards(SuitEnum.DIAMOND, 10, 2)); //12

        ps.getPlayers().get(0).addFirstCards(drawCards(SuitEnum.SPADE, 10, 1)); //21
        ps.getPlayers().get(1).addFirstCards(drawCards(SuitEnum.SPADE, 9, 8)); //17
        ps.getPlayers().get(2).addFirstCards(drawCards(SuitEnum.SPADE, 7, 5)); //12
        ps.getPlayers().get(3).addFirstCards(drawCards(SuitEnum.SPADE, 6, 4)); //10
        ps.getPlayers().get(4).addFirstCards(Arrays.asList(
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.K.getAlphabet()),
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.J.getAlphabet()))); //20
    }

    //bust ================================
    @DisplayName("딜러만 bust")
    @Test
    void bustD() {
        drawCard_bustD();

        game.checkBust(ps);
        if(ps.getDealer().isBust()) { //딜러 bust 이면 게임 종료
            game.modProfitByDealerBust(ps);
        }

        assertThat(ps.getDealer().getProfit()).isEqualTo( -(P0_BET + P1_BET + P2_BET + P3_BET + P4_BET));
        assertThat(ps.getPlayers().get(0).getProfit()).isEqualTo( P0_BET);
        assertThat(ps.getPlayers().get(1).getProfit()).isEqualTo( P1_BET);
        assertThat(ps.getPlayers().get(2).getProfit()).isEqualTo( P2_BET);
        assertThat(ps.getPlayers().get(3).getProfit()).isEqualTo( P3_BET);
        assertThat(ps.getPlayers().get(4).getProfit()).isEqualTo( P4_BET);
    }

    void drawCard_bustD() {
        ps.getDealer().addFirstCards(drawCards(SuitEnum.DIAMOND, 10, 2));
        ps.getDealer().addCard(new Card(SuitEnum.CLOVER.getSuit(), 10));  //22

        ps.getPlayers().get(0).addFirstCards(drawCards(SuitEnum.SPADE, 10, 2)); //12
        ps.getPlayers().get(1).addFirstCards(drawCards(SuitEnum.SPADE, 9, 8)); //17
        ps.getPlayers().get(2).addFirstCards(drawCards(SuitEnum.SPADE, 7, 5)); //12
        ps.getPlayers().get(3).addFirstCards(drawCards(SuitEnum.SPADE, 6, 4)); //10
        ps.getPlayers().get(4).addFirstCards(Arrays.asList(
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.K.getAlphabet()),
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.J.getAlphabet()))); //20
    }

    @DisplayName("딜러랑 플레이어 bust")
    @Test
    void bustDP() {
        drawCard_bustDP();

        game.checkBust(ps);
        if(ps.getDealer().isBust()) {
            game.modProfitByDealerBust(ps);
        }

        assertThat(ps.getDealer().getProfit()).isEqualTo(P0_BET - (P1_BET + P2_BET + P3_BET + P4_BET));
        assertThat(ps.getPlayers().get(0).getProfit()).isEqualTo( -P0_BET);
        assertThat(ps.getPlayers().get(1).getProfit()).isEqualTo( P1_BET);
        assertThat(ps.getPlayers().get(2).getProfit()).isEqualTo( P2_BET);
        assertThat(ps.getPlayers().get(3).getProfit()).isEqualTo( P3_BET);
        assertThat(ps.getPlayers().get(4).getProfit()).isEqualTo( P4_BET);
    }

    void drawCard_bustDP() {
        ps.getDealer().addFirstCards(drawCards(SuitEnum.DIAMOND, 10, 2));
        ps.getDealer().addCard(new Card(SuitEnum.CLOVER.getSuit(), 10));  //22

        ps.getPlayers().get(0).addFirstCards(drawCards(SuitEnum.SPADE, 10, 2));
        ps.getPlayers().get(0).addCard(new Card(SuitEnum.HEART.getSuit(), 10));  //22
        ps.getPlayers().get(1).addFirstCards(drawCards(SuitEnum.SPADE, 9, 8)); //17
        ps.getPlayers().get(2).addFirstCards(drawCards(SuitEnum.SPADE, 7, 5)); //12
        ps.getPlayers().get(3).addFirstCards(drawCards(SuitEnum.SPADE, 6, 4)); //10
        ps.getPlayers().get(4).addFirstCards(Arrays.asList(
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.K.getAlphabet()),
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.J.getAlphabet()))); //20
    }

    @DisplayName("플레이어만 bust")
    @Test
    void bustP() {
        drawCard_bustP();

        game.checkBust(ps);
        game.modProfit(ps);

        assertThat(ps.getDealer().getProfit()).isEqualTo( -(P1_BET + P4_BET) + (P0_BET + P3_BET));
        assertThat(ps.getPlayers().get(0).getProfit()).isEqualTo( -P0_BET);
        assertThat(ps.getPlayers().get(1).getProfit()).isEqualTo( P1_BET);
        assertThat(ps.getPlayers().get(2).getProfit()).isEqualTo( ZERO);
        assertThat(ps.getPlayers().get(3).getProfit()).isEqualTo( -P3_BET);
        assertThat(ps.getPlayers().get(4).getProfit()).isEqualTo( P4_BET);
    }

    void drawCard_bustP() {
        ps.getDealer().addFirstCards(drawCards(SuitEnum.DIAMOND, 10, 2)); //12

        ps.getPlayers().get(0).addFirstCards(drawCards(SuitEnum.SPADE, 10, 2));
        ps.getPlayers().get(0).addCard(new Card(SuitEnum.HEART.getSuit(), 10));  //22
        ps.getPlayers().get(1).addFirstCards(drawCards(SuitEnum.SPADE, 9, 8)); //17
        ps.getPlayers().get(2).addFirstCards(drawCards(SuitEnum.SPADE, 7, 5)); //12
        ps.getPlayers().get(3).addFirstCards(drawCards(SuitEnum.SPADE, 6, 4)); //10
        ps.getPlayers().get(4).addFirstCards(Arrays.asList(
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.K.getAlphabet()),
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.J.getAlphabet()))); //20
    }

    //maxNum ================================
    @DisplayName("blackjack, bust 없을 떄")
    @Test
    void maxNum() {
        drawCard_maxNum();

        game.checkBlackjack(ps);
        game.checkBust(ps);
        game.modProfitByNum(ps);

        assertThat(ps.getDealer().getProfit()).isEqualTo( -(P0_BET) + (P1_BET + P2_BET + P3_BET));
        assertThat(ps.getPlayers().get(0).getProfit()).isEqualTo( P0_BET);
        assertThat(ps.getPlayers().get(1).getProfit()).isEqualTo( -P1_BET);
        assertThat(ps.getPlayers().get(2).getProfit()).isEqualTo( -P2_BET);
        assertThat(ps.getPlayers().get(3).getProfit()).isEqualTo( -P3_BET);
        assertThat(ps.getPlayers().get(4).getProfit()).isEqualTo( ZERO);
    }

    void drawCard_maxNum() {
        ps.getDealer().addFirstCards(drawCards(SuitEnum.DIAMOND, 10, 2));
        ps.getDealer().addCard(new Card(SuitEnum.CLOVER.getSuit(), 8));  //20

        ps.getPlayers().get(0).addFirstCards(drawCards(SuitEnum.SPADE, 10, 2));
        ps.getPlayers().get(0).addCard(new Card(SuitEnum.HEART.getSuit(), 9));  //21
        ps.getPlayers().get(1).addFirstCards(drawCards(SuitEnum.SPADE, 9, 8)); //17
        ps.getPlayers().get(2).addFirstCards(drawCards(SuitEnum.SPADE, 7, 5)); //12
        ps.getPlayers().get(3).addFirstCards(drawCards(SuitEnum.SPADE, 6, 4)); //10
        ps.getPlayers().get(4).addFirstCards(Arrays.asList(
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.K.getAlphabet()),
                new Card(SuitEnum.SPADE.getSuit(), 10, AlphabetEnum.J.getAlphabet()))); //20
    }


    public List<Card> drawCards(SuitEnum suitEnum, int num1, int num2) {
        if(num2 == A_NUM) {
            return Arrays.asList(new Card(suitEnum.getSuit(), num1), new Card(suitEnum.getSuit(), AlphabetEnum.A.getNum(), AlphabetEnum.A.getAlphabet()));
        }
        return Arrays.asList(new Card(suitEnum.getSuit(), num1), new Card(suitEnum.getSuit(), num2));
    }

    //ONE POINT FIVE
    int multiplyOPF(int bet) {
        return (int) (bet * 1.5);
    }

}
