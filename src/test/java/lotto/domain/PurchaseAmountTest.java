package lotto.domain;

import lotto.exception.ErrorCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -3, -1000})
    void 구입_금액이_1000원_미만이면_예외가_발생한다(int input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_AMOUNT_NOT_ENOUGH.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1500, 3500, 5500})
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다(int input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_AMOUNT_INVALID_UNIT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 3000, 5000})
    void 구입_금액이_1000원_단위이면_테스트에_성공한다(int input) {
        // given by parameter

        // when & then
        assertThatCode(() -> new PurchaseAmount(input))
                .doesNotThrowAnyException();
    }
}
