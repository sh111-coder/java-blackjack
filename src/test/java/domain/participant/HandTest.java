package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    private Hand hand;

    @BeforeEach
    void init() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.CLOVER, Denomination.JACK));
        cards.add(new Card(Suit.CLOVER, Denomination.TWO));
        this.hand = new Hand(cards);
    }

    @Test
    @DisplayName("카드를 받아서 손패에 추가한다.")
    void addHandCards() {
        Card cardToAdd = new Card(Suit.SPADE, Denomination.ACE);
        hand.addCard(cardToAdd);
        List<Card> afterAddCards = hand.getCards();

        Assertions.assertThat(afterAddCards.size()).isEqualTo(3);
        Assertions.assertThat(afterAddCards).contains(cardToAdd);
    }

    @Test
    @DisplayName("Ace가 포함된 경우 1, 11 중 최적의 값을 선택해서 합을 구한다.")
    void getOptimalSumValueWhenAceContain() {
        // given
        Card card = new Card(Suit.DIAMOND, Denomination.ACE);
        hand.addCard(card);

        // when, then
        assertThat(hand.calculateOptimalCardValueSum()).isEqualTo(13);
        assertThat(hand.calculateOptimalCardValueSum()).isNotEqualTo(22);
    }

    @Test
    @DisplayName("Ace가 포함되지 않으면 단순 합을 구한다.")
    void getOptimalSumValueWhenAceNotContain() {
        // given
        Card card = new Card(Suit.DIAMOND, Denomination.THREE);
        hand.addCard(card);

        // when, then
        assertThat(hand.calculateOptimalCardValueSum()).isEqualTo(15);
    }

}
