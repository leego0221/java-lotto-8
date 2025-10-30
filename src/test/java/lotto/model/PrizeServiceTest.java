package lotto.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeServiceTest {

    private final PrizeService prizeService = new PrizeService();

    @ParameterizedTest
    @CsvSource(value = {"1,2000000000", "2,30000000", "3,1500000", "4,50000", "5,5000"})
    void 등수에_따라_당첨_금액을_부여한다(int rank, long winningPrize) {
        // given by parameter

        // when
        long prize = prizeService.payPrize(rank);

        // then
        assertThat(prize).isEqualTo(winningPrize);
    }
}