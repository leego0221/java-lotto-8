package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankingServiceTest {

    private final LottoRankingService lottoRankingService = new LottoRankingService();

    @Test
    void 로또_번호가_당첨_번호_6개와_일치하면_1등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6"));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoRankingService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(1);
    }

    @Test
    void 로또_번호가_당청_번호_5개와_보너스_번호와_일치하면_2등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        WinningNumbers winningNumbers = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6"));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoRankingService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(2);
    }

    @Test
    void 로또_번호가_당첨_번호_5개와_일치하면_3등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 11));
        WinningNumbers winningNumbers = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6"));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoRankingService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(3);
    }

    @Test
    void 로또_번호가_당첨_번호_4개와_일치하면_4등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 11, 12));
        WinningNumbers winningNumbers = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6"));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoRankingService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(4);
    }

    @Test
    void 로또_번호가_당첨_번호_3개와_일치하면_5등이다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 11, 12, 13));
        WinningNumbers winningNumbers = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6"));
        BonusNumber bonusNumber = new BonusNumber(7);

        // when
        int rank = lottoRankingService.determineRank(lotto, winningNumbers, bonusNumber);

        // then
        assertThat(rank).isEqualTo(5);
    }
}