package vendingmachine.view.formatter;

import java.util.Map.Entry;
import vendingmachine.common.Symbol;
import vendingmachine.domain.Coin;

public class OutputFomatter {
    public static final String SEPARATOR = Symbol.COMMA;

    public static String formatMoney(int money) {
        return String.format("%,d", money);
    }

    public static int toCoinAmount(Entry<Coin, Integer> coinStorageDetail) {
        return coinStorageDetail.getKey().getAmount();
    }

    public static int toCoinCount(Entry<Coin, Integer> coinStorageDetail) {
        return coinStorageDetail.getValue();
    }
}
