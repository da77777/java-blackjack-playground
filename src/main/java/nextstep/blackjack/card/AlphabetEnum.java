package nextstep.blackjack.card;

public enum AlphabetEnum {
    A("A", 1)
    , K("K", 10)
    , Q("Q", 10)
    , J("J", 10)
    ;

    private final String alphabet; //컴파일러가 알아서 붙여주지만 명시적으로 표현하기 위해 final 붙이는 걸 지향
    private final int num;

    public String getAlphabet() {
        return alphabet;
    }

    public int getNum() {
        return num;
    }

    AlphabetEnum(String alphabet, int num) {
        this.alphabet = alphabet;
        this.num = num;
    }
}
