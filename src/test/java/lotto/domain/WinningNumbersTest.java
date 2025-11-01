package lotto.domain;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

    @ParameterizedTest
    @MethodSource("generateInvalidSizeWinningNumbers")
    void 당첨_번호의_개수가_6개가_아니면_예외가_발생한다(List<Integer> input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_NUMBERS_INVALID_SIZE.getMessage());
    }

    @Test
    void 당첨_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        // given
        List<Integer> input = List.of(1, 2, 3, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_NUMBERS_DUPLICATE.getMessage());
    }

    @ParameterizedTest
    @MethodSource("generateInvalidRangeWinningNumbers")
    void 당첨_번호_중_하나라도_1이상_45이하가_아니면_예외가_발생한다(List<Integer> input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_NUMBERS_INVALID_RANGE.getMessage());
    }

    @Test
    void 당첨_번호_객체는_외부에서_변경을_시도하면_예외가_발생한다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when
        List<Integer> numbers = winningNumbers.getWinningNumbers();

        // then
        assertThatThrownBy(() -> numbers.set(0, 10))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    static Stream<Arguments> generateInvalidSizeWinningNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }

    static Stream<Arguments> generateInvalidRangeWinningNumbers() {
        return Stream.of(
                Arguments.of(List.of(10, 15, 20, 25, 30, 70)),
                Arguments.of(List.of(10, 15, 20, 25, 75, 70)),
                Arguments.of(List.of(10, 15, 20, 80, 75, 70)),
                Arguments.of(List.of(10, 15, 85, 80, 75, 70)),
                Arguments.of(List.of(10, 90, 85, 80, 75, 70)),
                Arguments.of(List.of(-10, -1, 0, 46, 77, 99))
        );
    }
}
