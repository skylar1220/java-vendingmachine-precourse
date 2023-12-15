package vendingmachine.domain;

public class VendingMachine {
    private final CoinStorage vendingCoinStorage;
    private final Products products;
    private final MoneyInserted moneyInserted;

    public VendingMachine(CoinStorage vendingCoinStorage, Products products, MoneyInserted moneyInserted) {
        this.vendingCoinStorage = vendingCoinStorage;
        this.products = products;
        this.moneyInserted = moneyInserted;
    }

    public static VendingMachine of(CoinStorage vendingCoinStorage, Products products) {
        return new VendingMachine(vendingCoinStorage, products, MoneyInserted.init());
    }

    public void insertMoney(MoneyInserted moneyInserted) {
        this.moneyInserted.apply(moneyInserted);
    }
}
