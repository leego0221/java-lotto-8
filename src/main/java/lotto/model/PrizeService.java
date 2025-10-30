package lotto.model;

public class PrizeService {

    public long payPrize(int rank) {
        if (rank == 1) {
            return 2_000_000_000L;
        }
        if (rank == 2) {
            return 30_000_000L;
        }
        if (rank == 3) {
            return 1_500_000L;
        }
        if (rank == 4) {
            return 50_000L;
        }
        if (rank == 5) {
            return 5_000L;
        }

        return 0L;
    }
}
