package nextstep.blackjack.card.domain;

import nextstep.blackjack.card.AlphabetEnum;
import nextstep.blackjack.card.SuitEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrumpCard {
    // 싱글톤 참고
    // https://letyarch.blogspot.com/2019/04/singleton-synchronized_8.html
    // https://junghyungil.tistory.com/150
    private static volatile TrumpCard singleton; //volatile : 캐시 불일치 이슈 방지
    private TrumpCard() {}
    public static TrumpCard getInstance() {
        if(singleton == null) { //객체 생성 필요할 때만 락을 얻도록 메서드 내부에서 synchronized
            synchronized (TrumpCard.class) {
               if(singleton == null) { //동기화 블록 들어온 후 한 번 더 검사
                    singleton = new TrumpCard();
                }
            }
        }
        return singleton;
    }

    static List<Card> cards = new ArrayList<>();
    Random random = new Random();

    public List<Card> getCards() {
        return cards;
    }

    //카드 만들기
    public static List<Card> createTrumpCard() {
        cards.clear();
        SuitEnum[] se = SuitEnum.values();
        for (SuitEnum s : se) {
            createBySuit(s.getSuit());
        }
        return cards;
    }

    private static List<Card> createBySuit(String suit) {
        //숫자카드
        for(int i = 2; i < 11; i++) {
            cards.add(new Card(suit, i));
        }
        //알파벳카드
        AlphabetEnum[] alphabet = AlphabetEnum.values();
        for (AlphabetEnum a : alphabet) {
            cards.add(new Card(suit, a.getNum(), a.getAlphabet()));
        }

        return cards;
    }
    
    //카드 빼기
    public Card drawCard() {
        return cards.remove(getRandomNum());
    }

    //게임 시작 시 카드 2장 뺴기
    public List<Card> drawFirstCard() {
        return Arrays.asList(drawCard(), drawCard());
    }

    private int getRandomNum() {
        return random.nextInt(cards.size());
    }

}
