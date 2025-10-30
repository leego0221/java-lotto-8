package lotto.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeServiceTest {

    private final PrizeService prizeService = new PrizeService();

    @ParameterizedTest
    @CsvSource(value = {"1,2_000_000_000L", "2,30_000_000L", "3,1_500_000L", "4,50_000L", "5,5_000L"})
    void 등수에_따라_당첨_금액을_부여한다(int rank, long winningPrize) {
        // given by parameter

        // when
        long prize = prizeService.payPrize(rank);

        // then
        assertThat(prize).isEqualTo(winningPrize);
    }
}