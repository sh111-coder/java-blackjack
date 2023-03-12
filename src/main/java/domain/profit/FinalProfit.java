package domain.profit;

import java.util.Objects;

public class FinalProfit {

    private static final double NO_PROFIT = 0.0;
    private final double profit;

    public FinalProfit(final double profit) {
        this.profit = profit;
    }

    public FinalProfit add(FinalProfit finalProfit) {
        return new FinalProfit(this.profit + finalProfit.profit);
    }

    public FinalProfit convertToNegative() {
        if (this.profit == NO_PROFIT) {
            return new FinalProfit(NO_PROFIT);
        }
        return new FinalProfit(this.profit * -1);
    }

    public double getProfit() {
        return profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalProfit that = (FinalProfit) o;
        return profit == that.profit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit);
    }

    @Override
    public String toString() {
        return "FinalProfit{" +
                "profit=" + profit +
                '}';
    }
}
