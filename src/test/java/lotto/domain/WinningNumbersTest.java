package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @ParameterizedTest
    @MethodSource("generateInvalidSizeWinningNumbers")
    void 당첨_번호가_6개가_아니면_예외가_발생한다(List<Integer> input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 당첨_번호_중에_중복이_있으면_예외가_발생한다() {
        // given
        List<Integer> input = List.of(1, 2, 3, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @Test
    void 하나의_당첨_번호가_1에서_45_사이면_테스트에_성공한다() {
        // given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatCode(() -> new WinningNumbers(input))
                .doesNotThrowAnyException();
    }

    @Test
    void 하나의_당첨_번호가_1에서_45_사이를_벗어나면_예외가_발생한다() {
        // given
        List<Integer> input = List.of(-1, 0, 3, 15, 36, 100);

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    static Stream<Arguments> generateInvalidSizeWinningNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }
}
