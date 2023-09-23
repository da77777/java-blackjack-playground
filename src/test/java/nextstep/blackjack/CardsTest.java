package nextstep.blackjack;

import nextstep.blackjack.card.Cards;
import org.testng.annotations.Test;

public class CardsTest {

    //assertThat.hasSize(n)
    @Test
    public void temp() {
        Cards cards = new Cards();

        System.out.println(cards.draw().toString());
    }

}
