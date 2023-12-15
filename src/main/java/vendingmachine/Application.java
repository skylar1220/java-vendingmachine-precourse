package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.printer.ConsolePrinter;
import vendingmachine.view.printer.Printer;
import vendingmachine.view.reader.ConsoleReader;
import vendingmachine.view.reader.Reader;

public class Application {
    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Printer printer = new ConsolePrinter();

        InputView inputView = InputView.of(reader, printer);
        OutputView outputView = new OutputView(printer);

        VendingMachineController vendingMachineController = new VendingMachineController(inputView, outputView);
        vendingMachineController.run();

    }
}
