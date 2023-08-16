package blackjack.participant;

import nextstep.blackjack.card.AlphabetEnum;
import nextstep.blackjack.card.SuitEnum;
import nextstep.blackjack.card.domain.Card;
import nextstep.blackjack.participants.domain.Dealer;
import nextstep.blackjack.participants.domain.Participants;
import nextstep.blackjack.participants.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class ParticipantTest {

    private static final int P0_BET = 100;
    private static final int P1_BET = 200;
    private static final int P2_BET = 300;
    private static final int A_NUM = 1;

    Participants ps = null;

    @BeforeEach
    public void gameReady() {
        List<Player> players = new ArrayList<>();
        players.addAll(Arrays.asList(
                new Player("A", P0_BET),
                new Player("B", P1_BET),
                new Player("C", P2_BET)));

        ps = new Participants(new Dealer(), players);

    }

    @ParameterizedTest
    @CsvSource(value = {"1, true, 20", "2, true, 21", "3, true, 12"})
    void make_nonBust(int num, boolean expected, int totalNum) {
        Player p = ps.getPlayers().get(0);
        p.addFirstCards(drawCards(SuitEnum.SPADE, 8, 1));
        p.addCard(new Card(SuitEnum.HEART.getSuit(), num));

        p.makeNumMax();

        assertThat(p.getTotalNum() <= 21).isEqualTo(expected);
        assertThat(p.getTotalNum()).isEqualTo(totalNum);
    }

    public List<Card> drawCards(SuitEnum suitEnum, int num1, int num2) {
        if(num2 == A_NUM) {
            return Arrays.asList(new Card(suitEnum.getSuit(), num1), new Card(suitEnum.getSuit(), AlphabetEnum.A.getNum(), AlphabetEnum.A.getAlphabet()));
        }
        return Arrays.asList(new Card(suitEnum.getSuit(), num1), new Card(suitEnum.getSuit(), num2));
    }

}
