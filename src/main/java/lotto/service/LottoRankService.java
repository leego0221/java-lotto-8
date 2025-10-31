package lotto.service;

import lotto.domain.*;

import java.util.Map;

public class LottoRankService {

    private static final long INITIAL_TOTAL_PRIZE = 0L;

    private final LottoRankCounter lottoRankCounter;

    public LottoRankService(LottoRankCounter lottoRankCounter) {
        this.lottoRankCounter = lottoRankCounter;
    }

    public void determineRank(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int matchingCount = countMatchingNumbers(lotto, winningNumbers);
        boolean isBonusNumberMatched = isBonusNumberMatched(lotto, bonusNumber);

        lottoRankCounter.update(matchingCount, isBonusNumberMatched);
    }

    public Map<LottoRank, Integer> getRanks() {
        return lottoRankCounter.getLottoResult();
    }

    public double calculateProfitRate(int totalPurchase) {
        Map<LottoRank, Integer> lottoResult = lottoRankCounter.getLottoResult();

        Long totalPrize = lottoResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> entry.getKey().getPrize() * entry.getValue())
                .reduce(INITIAL_TOTAL_PRIZE, Long::sum);

        return (double) totalPrize / totalPurchase * 100;
    }

    private int countMatchingNumbers(Lotto lotto, WinningNumbers winningNumbers) {
        return lotto.countMatchingNumbers(winningNumbers);
    }

    private boolean isBonusNumberMatched(Lotto lotto, BonusNumber bonusNumber) {
        return lotto.isBonusNumberMatched(bonusNumber);
    }
}
