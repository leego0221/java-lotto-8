package lotto.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    private static final String ERROR_MESSAGE = "[ERROR]";
    private final InputValidator inputValidator = new InputValidator();

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void 입력이_공백_계열이면_예외가_발생한다(String input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> inputValidator.validateIsBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"333333333333333", "3.3", "a"})
    void 입력이_Integer_타입이_아니면_예외가_발생한다(String input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> inputValidator.validateIsInteger(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }
}
