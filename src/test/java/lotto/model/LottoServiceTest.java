package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoServiceTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 보너스_번호가_당첨_번호들과_중복되지_않으면_테스트에_성공한다() {
        // given
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatCode(() -> new LottoService(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    void 보너스_번호가_당첨_번호들과_중복되면_예외가_발생한다() {
        // given
        BonusNumber bonusNumber = new BonusNumber(3);
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatThrownBy(() -> new LottoService(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }
}
