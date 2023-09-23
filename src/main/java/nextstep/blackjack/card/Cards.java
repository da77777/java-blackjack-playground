package nextstep.blackjack.card;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class Cards {
    private static final Queue<Card> CARDS = new ConcurrentLinkedDeque<>();

    static {
        List<Card> pCards = new ArrayList<>();
        Suit[] suits = Suit.values();
        for (Suit suit : suits) {
            pCards.addAll(createCards(suit));
        }

        Collections.shuffle(pCards);
        CARDS.addAll(pCards);
    }

    private static List<Card> createCards(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(d -> new Card(suit, d))
                .collect(Collectors.toList());
    }

    public Card draw() {
        return CARDS.poll();
    }
}
