package vendingmachine.view.formatter;

import java.util.Map.Entry;
import vendingmachine.domain.Coin;

public class OutputFomatter {
    public static int toCoinAmount(Entry<Coin, Integer> coinStorageDetail) {
        return coinStorageDetail.getKey().getAmount();
    }

    public static int toCoinCount(Entry<Coin, Integer> coinStorageDetail) {
        return coinStorageDetail.getValue();
    }
}
