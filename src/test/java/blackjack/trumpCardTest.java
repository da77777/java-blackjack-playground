package blackjack;

import nextstep.blackjack.card.domain.Card;
import nextstep.blackjack.card.domain.TrumpCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

public class trumpCardTest {
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


