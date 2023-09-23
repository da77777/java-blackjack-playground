package blackjack;

import nextstep.blackjack.card.AlphabetEnum;
import nextstep.blackjack.card.SuitEnum;
import nextstep.blackjack.card.domain.Card;
import nextstep.blackjack.card.domain.TrumpCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrumpCardTest {
    TrumpCard trumpCard = TrumpCard.getInstance();

    @BeforeEach
    void createCard52() {
        TrumpCard.createTrumpCard();
    }

    @Test
    void cardCount52() {
        assertThat(trumpCard.getCards().size()).isEqualTo(52);
    }

    @Test
    void drawCard() {
        List<Card> pickCards = Arrays.asList(trumpCard.drawCard(), trumpCard.drawCard());
        assertThat(pickCards.size()).isEqualTo(2);
        assertThat(trumpCard.getCards().size()).isEqualTo(50);
    }



}


