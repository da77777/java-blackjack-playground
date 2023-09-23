package nextstep.blackjack.participants.domain;

import nextstep.blackjack.card.domain.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participant {

    protected List<Card> pCards = new ArrayList<>();
    protected int totalNum = 0;
    protected int profit = 0;
    protected boolean bust = false;
    protected boolean blackjack = false;

    public List<Card> getPCards() {
        return pCards;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getProfit() {
        return profit;
    }

    public boolean isBlackjack() {
        return blackjack;
    }

    public boolean isBust() {
        return bust;
    }

    public void addFirstCards(List<Card> cards) {
        pCards.addAll(cards);
    }

    public void addCard(Card card) {
        pCards.add(card);
    }

    public void modIsBlackjack(boolean blackjack) {
        this.blackjack = blackjack;
    }

    public void modIsBust(boolean bust) {
        this.bust = bust;
    }

    public void addProfit(int amount) {
        this.profit += amount;
    }

    public int makeNumMax() {
        Map<Boolean, List<Card>> cardGroupByAce = separateAceCards();
        List<Card> normalCards = cardGroupByAce.getOrDefault(false, new ArrayList<>());
        List<Card> aceCards = cardGroupByAce.getOrDefault(true, new ArrayList<>());

        int normalNum = normalCards.stream().mapToInt(Card::getNum).sum();
        if(!aceCards.isEmpty() && normalNum <= 10) {
            aceCards.get(0).setAceCardNum(11);
        }

        totalNum = pCards.stream()
                .mapToInt(Card::getNum)
                .sum();

        return totalNum;
    }

    private Map<Boolean, List<Card>> separateAceCards() {
        return pCards.stream().collect(Collectors.groupingBy(c -> "A".equals(c.getAlphabet())));
    }

    public String getAllCardsStr() {
        return pCards.stream()
                .map(c -> c.getNum() + c.getSuit())
                .collect(Collectors.joining(", "));
    }




}
