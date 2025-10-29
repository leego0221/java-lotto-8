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

    @Test
    void 보너스_번호가_당첨_번호들과_중복되지_않으면_테스트에_성공한다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when & then
        assertThatCode(() -> validator.validateDuplicate(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    void 보너스_번호가_당첨_번호들과_중복되면_예외가_발생한다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 3;

        // when & then
        assertThatThrownBy(() -> validator.validateDuplicate(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }
}
