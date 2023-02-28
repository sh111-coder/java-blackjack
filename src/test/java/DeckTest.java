import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DeckTest {

    @Test
    @DisplayName("초기 덱의 카드 수는 52장이어야한다.")
    void checkDeckSize() {

        Assertions.assertThat(Deck.cards.size()).isEqualTo(52);
    }

    @ParameterizedTest
    @CsvSource(value = {"3하트, 3", "5다이아몬드, 5", "10클로버, 10"})
    @DisplayName("각 카드에 해당하는 숫자를 정상적으로 추출한다.")
    void shouldSuccessExtractCardNumber(String card, int cardNumber) {
        Assertions.assertThat(Deck.extractCardNumber(card)).isEqualTo(cardNumber);
    }
}
