package vendingmachine.controller;

import java.util.function.Function;
import java.util.function.Supplier;
import vendingmachine.domain.CoinStorage;
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

        vendingMachine.insertMoney(inputView.inputMoneyInserted());
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
