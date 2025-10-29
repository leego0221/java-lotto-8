package lotto.validation;

import lotto.model.WinningNumbers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {

    private static final String ERROR_MESSAGE = "[ERROR]";
    private final Validator validator = new Validator();

    @Test
    void 구입_금액이_1000원_단위이면_테스트에_성공한다() {
        // given
        int purchaseAmount = 3000;

        // when & then
        assertThatCode(() -> validator.validatePurchaseAmount(purchaseAmount))
                .doesNotThrowAnyException();
    }

    @Test
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        // given
        int purchaseAmount = 3500;

        // when & then
        assertThatThrownBy(() -> validator.validatePurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }
}
