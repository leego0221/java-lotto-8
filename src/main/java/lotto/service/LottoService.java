package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.*;
import lotto.exception.ErrorCode;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoService {

    private static final int LOTTO_PRIZE = 1000;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final long INITIAL_TOTAL_PRIZE = 0L;

    private final LottoRankCounter lottoRankCounter;

    public LottoService(LottoRankCounter lottoRankCounter) {
        this.lottoRankCounter = lottoRankCounter;
    }

    public List<Lotto> purchase(PurchaseAmount purchaseAmount) {
        int purchaseCount = purchaseAmount.getPurchaseAmount() / LOTTO_PRIZE;

        return IntStream.range(0, purchaseCount)
                .mapToObj(i -> generateLotto(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT))
                .toList();
    }

    public void checkDuplicate(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        boolean isDuplicate = winningNumbers.getWinningNumbers()
                .stream()
                .anyMatch(winningNumber -> winningNumber == bonusNumber.getBonusNumber());

        if (isDuplicate) {
            throw new IllegalArgumentException(ErrorCode.ERROR_NUMBERS_AND_BONUS_DUPLICATE.getMessage());
        }
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

    public long payPrize(LottoRank lottoRank) {
        return lottoRank.getPrize();
    }

    private Lotto generateLotto(int start, int end, int count) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(start, end, count);
        return new Lotto(numbers);
    }

    private int countMatchingNumbers(Lotto lotto, WinningNumbers winningNumbers) {
        return lotto.countMatchingNumbers(winningNumbers);
    }

    private boolean isBonusNumberMatched(Lotto lotto, BonusNumber bonusNumber) {
        return lotto.isBonusNumberMatched(bonusNumber);
    }
}
