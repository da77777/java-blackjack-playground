package nextstep.blackjack.card;

public enum SuitEnum {
    SPADE("스페이드")
    , HEART("하트")
    , DIAMOND("다이아몬드")
    , CLOVER("클로버")
    ;

    private final String suit;

    public String getSuit() {
        return suit;
    }

    SuitEnum(String suit) {
        this.suit = suit;
    }
}
