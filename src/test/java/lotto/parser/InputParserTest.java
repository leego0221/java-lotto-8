package lotto.parser;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputParserTest {

    @Test
    void 쉼표_기준으로_구분되고_각_요소가_정수면_테스트에_성공한다() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> winningNumbers = InputParser.parseWinningNumbers(input);

        // then
        assertThat(winningNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 쉼표_기준으로_구분되고_각_요소가_앞뒤_공백이_있는_정수여도_테스트에_성공한다() {
        // given
        String input = " 1,  2,  3 , 4  ,5  ,6 ";

        // when
        List<Integer> winningNumbers = InputParser.parseWinningNumbers(input);

        // then
        assertThat(winningNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,", "1,,3", ",2,3", "1,2, ", "1, ,3", " ,2,3"})
    void 쉼표_기준으로_구분되어도_각_요소가_공백_계열이면_테스트에_실패한다(String input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_PARSED_BLANK.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,2,3", "1,a,3", "1,2,a"})
    void 쉼표_기준으로_구분되어도_각_요소가_정수가_아니면_테스트에_실패한다(String input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_PARSED_NOT_INTEGER.getMessage());
    }
}
