package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.IntStream;

public class LottoService {

    private static final int LOTTO_PRIZE = 1000;
    private static final int LOTTO_START_NUM = 1;
    private static final int LOTTO_END_NUM = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public List<Lotto> purchase(PurchaseAmount purchaseAmount) {
        int purchaseCount = purchaseAmount.getPurchaseAmount() / LOTTO_PRIZE;

        return IntStream.range(0, purchaseCount)
                .mapToObj(i -> generateLotto(LOTTO_START_NUM, LOTTO_END_NUM, LOTTO_NUMBER_COUNT))
                .toList();
    }

    public void checkDuplicate(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        boolean isDuplicate = winningNumbers.getWinningNumbers()
                .stream()
                .anyMatch(winningNumber -> winningNumber == bonusNumber.getBonusNumber());

        if (isDuplicate) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호가 중복됩니다.");
        }
    }

    public int determineRank(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int count = countMatching(lotto, winningNumbers);
        boolean isMatched = isBonusNumberMatched(lotto, bonusNumber);

        if (count == 6) {
            return 1;
        }
        if (count == 5 && isMatched) {
            return 2;
        }
        if (count == 5) {
            return 3;
        }
        if (count == 4) {
            return 4;
        }
        if (count == 3) {
            return 5;
        }

        return 0; // 해당 없음
    }

    public String calculateProfitRate(long totalPrize, int totalPurchase) {
        double profitRate = (double) totalPrize / totalPurchase * 100;
        return String.format("%.1f", profitRate);
    }

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

    private Lotto generateLotto(int start, int end, int count) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(start, end, count);
        return new Lotto(numbers);
    }

    private int countMatching(Lotto lotto, WinningNumbers winningNumbers) {
        return lotto.countMatching(winningNumbers);
    }

    private boolean isBonusNumberMatched(Lotto lotto, BonusNumber bonusNumber) {
        return lotto.isBonusNumberMatched(bonusNumber);
    }
}
