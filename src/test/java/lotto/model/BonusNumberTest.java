package lotto.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 15, 45})
    void 보너스_번호가_1에서_45_사이면_테스트에_성공한다(int input) {
        // given by parameter

        // when & then
        assertThatCode(() -> new BonusNumber(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 100})
    void 보너스_번호가_1에서_45_사이를_벗어나면_예외가_발생한다(int input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new BonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }
}
