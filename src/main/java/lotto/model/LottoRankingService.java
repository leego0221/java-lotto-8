package lotto.model;

public class LottoRankingService {

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

    private int countMatching(Lotto lotto, WinningNumbers winningNumbers) {
        return lotto.countMatching(winningNumbers);
    }

    private boolean isBonusNumberMatched(Lotto lotto, BonusNumber bonusNumber) {
        return lotto.isBonusNumberMatched(bonusNumber);
    }
}
