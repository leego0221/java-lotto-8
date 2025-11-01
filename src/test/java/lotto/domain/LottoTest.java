package lotto.domain;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("generateInvalidRangeLotto")
    void 로또_번호_중_하나라도_1이상_45이하가_아니면_예외가_발생한다(List<Integer> input) {
        // given by parameter

        // when & then
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.ERROR_LOTTO_INVALID_RANGE.getMessage());
    }

    @Test
    void 로또_객체는_외부에서_변경을_시도하면_예외가_발생한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        List<Integer> numbers = lotto.getNumbers();

        // then
        assertThatThrownBy(() -> numbers.set(0, 10))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 로또_번호와_당첨_번호가_3개_일치하면_테스트가_성공한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(2, 4, 6, 8, 10, 12));

        // when
        int matchingCount = lotto.countMatchingNumbers(winningNumbers);

        // then
        assertThat(matchingCount).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,true", "7,false"})
    void 로또_번호와_보너스_번호_일치_여부가_맞으면_테스트가_성공한다(int input, boolean result) {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(input);

        // when
        boolean isBonusNumberMatched = lotto.isBonusNumberMatched(bonusNumber);

        // then
        assertThat(isBonusNumberMatched).isEqualTo(result);
    }

    static Stream<Arguments> generateInvalidRangeLotto() {
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
