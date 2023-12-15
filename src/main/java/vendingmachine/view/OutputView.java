package vendingmachine.view;

import java.util.Map.Entry;
import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinStorage;
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
        vendingCoinStorage.getCoinStorage().entrySet().forEach(coinStorageDetail -> printCoinStorageDetail(coinStorageDetail));
    }

    private void printCoinStorageDetail(Entry<Coin, Integer> coinStorageDetail) {
        int coinAmount = OutputFomatter.toCoinAmount(coinStorageDetail);
        int count = OutputFomatter.toCoinCount(coinStorageDetail);
        printer.printLine("%d원 - %d개", coinAmount, count);
    }
}
