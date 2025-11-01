package lotto.domain;

import lotto.exception.ErrorCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 15, 45})
    void 보너스_번호가_1이상_45이하면_테스트가_성공한다(int input) {
        // given by parameter

        // when & then
        assertThatCode(() -> new BonusNumber(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100})
    void 보너스_번호가_1이상_45이하가_아니면_예외가_발생한다(int input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new BonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_BONUS_INVALID_RANGE.getMessage());
    }
}
