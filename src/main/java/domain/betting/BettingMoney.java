package domain.betting;

import util.Constants;

public class BettingMoney {

    private static final String LESS_OR_EQUAL_ZERO_ERROR_MESSAGE = "배팅 금액은 0보다 커야합니다.";

    private final int money;

    public BettingMoney(final int money) {
        validate(money);
        this.money = money;
    }

    private void validate(final int money) {
        if (isLessOrEqualZero(money)) {
            throw new IllegalArgumentException(Constants.ERROR_PREFIX + LESS_OR_EQUAL_ZERO_ERROR_MESSAGE);
        }
    }

    private boolean isLessOrEqualZero(int money) {
        return money <= 0;
    }
}
