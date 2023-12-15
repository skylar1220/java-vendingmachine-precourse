package vendingmachine.controller;

import java.util.function.Function;
import java.util.function.Supplier;
import vendingmachine.domain.CoinStorage;
import vendingmachine.domain.MoneyInserted;
import vendingmachine.domain.ProductName;
import vendingmachine.domain.Products;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CoinStorage vendingCoinStorage = inputView.inputCointStorage();
        outputView.printVendingCoinStorage(vendingCoinStorage);
        Products products = inputView.inputProducts();
        VendingMachine vendingMachine = VendingMachine.of(vendingCoinStorage, products);

        MoneyInserted moneyInserted = inputView.inputMoneyInserted();
        vendingMachine.insertMoney(moneyInserted);
        outputView.printMoneyInserted(moneyInserted);

        if (!vendingMachine.isBuyingAvailable()) {
            return; // 잔돈반환으로 가기
        }

        ProductName selectedProductName = getSelectedProductName(vendingMachine);
        vendingMachine.buy(selectedProductName);

        if (vendingMachine.isBuyingAvailable()) {
            return; // 상품명 입력으로 돌아가기
        }
        if (!vendingMachine.isBuyingAvailable()) {
            return; // 잔돈반환으로 가기
        }
        CoinStorage customerChangesStorage = vendingMachine.getCustomerChanges();
        outputView.printCustomerChanges(customerChangesStorage);
    }

    private ProductName getSelectedProductName(VendingMachine vendingMachine) {
        ProductName selectedProductName = inputView.inputSelectedProduct();
        vendingMachine.checkAvailableProduct(selectedProductName);
        return selectedProductName;
    }

    private <T> T readWithRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(supplier);
        }
    }

    private <T, R> R readWithRetry(Function<T, R> function, T input) {
        try {
            return function.apply(input);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readWithRetry(function, input);
        }
    }
}
