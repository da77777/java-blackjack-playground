package nextstep.blackjack.participants.domain;

import nextstep.blackjack.card.domain.Card;

import java.util.ArrayList;
import java.util.List;
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

    //카드 가져오기
    public void addFirstCards(List<Card> cards) {
        pCards.addAll(cards);
    }

    public void addCard(Card card) {
        pCards.add(card);
    }

    public List<Card> pickAceCards() {
        return pCards.stream()
                .filter(card -> "A".equals(card.getAlphabet()))
                .collect(Collectors.toList());
    }

    public List<Card> pickNormalCards() {
        return pCards.stream()
                .filter(card -> !"A".equals(card.getAlphabet()))
                .collect(Collectors.toList());
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

    public String getAllCardsStr() {
        return pCards.stream()
                .map(c -> c.getNum() + c.getSuit())
                .collect(Collectors.joining(", "));
    }

    public int makeNumMax() {
        List<Card> normalCards = pickNormalCards();
        List<Card> aceCards = pickAceCards();

        int normalNum = normalCards.stream().mapToInt(n -> n.getNum()).sum();
        if(!aceCards.isEmpty() && normalNum <= 10) {
            aceCards.get(0).setAceCardNum(11);
        }

        totalNum = pCards.stream()
                .mapToInt(Card::getNum)
                .sum();

        return totalNum;
    }


}
