package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.*;
import lotto.exception.ErrorCode;

import java.util.List;
import java.util.stream.IntStream;

public class LottoService {

    private static final int LOTTO_PRIZE = 1000;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

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

    private Lotto generateLotto(int start, int end, int count) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(start, end, count);
        return new Lotto(numbers);
    }
}
