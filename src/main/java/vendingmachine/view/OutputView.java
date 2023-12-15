package vendingmachine.view;

import java.util.Map.Entry;
import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinStorage;
import vendingmachine.domain.MoneyInserted;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.formatter.OutputFomatter;
import vendingmachine.view.printer.Printer;

public class OutputView {
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";
    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

//    public void printTemplate(Template rawTemplate) {
//        int template = OutputFomatter.toTemplate(rawTemplate);
//        printer.printLine("%d개", template);
//    }

    public void printExceptionMessage(String message) {
        printer.printLine(ERROR_MESSAGE_FORMAT + message);
        printer.printEmptyLine();
    }

    public void printVendingCoinStorage(CoinStorage vendingCoinStorage) {
        printer.printLine("자판기가 보유한 동전");
        vendingCoinStorage.getCoinStorage().entrySet().forEach(this::printCoinStorageDetail);
    }

    private void printCoinStorageDetail(Entry<Coin, Integer> coinStorageDetail) {
        int coinAmount = OutputFomatter.toCoinAmount(coinStorageDetail);
        int count = OutputFomatter.toCoinCount(coinStorageDetail);
        printer.printLine("%d원 - %d개", coinAmount, count);
    }

    public void printMoneyInserted(MoneyInserted moneyInserted) {
        printer.printLine("투입 금액: %d원", moneyInserted.getMoneyInserted());
    }

    public void printCustomerChanges(CoinStorage customerChangesStorage) {
        printer.printLine("잔돈");
        customerChangesStorage.getCoinStorage().entrySet().forEach(this::printCoinStorageDetail);

    }

    public void printMoneyInserted(VendingMachine vendingMachine) {
        printer.printLine("투입 금액: %d원", vendingMachine.getMoneyInserted().getMoneyInserted());
    }
}
