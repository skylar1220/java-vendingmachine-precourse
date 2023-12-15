package vendingmachine.view;


import vendingmachine.domain.CoinStorage;
import vendingmachine.util.converter.Converter;
import vendingmachine.view.printer.Printer;
import vendingmachine.view.reader.Reader;
import vendingmachine.view.validator.InputValidator;

public class InputView {
    //    public static final String SEPARATOR = Symbol.COMMA;
    private final Reader reader;
    private final Printer printer;
    private final InputValidator validator;

    private InputView(Reader reader, Printer printer, InputValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.validator = validator;
    }

    public static InputView of(Reader reader, Printer printer) {
        return new InputView(reader, printer, InputValidator.getInstance());
    }

    public CoinStorage inputCointStorage() {
        printer.printLine("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String inputMoney = reader.readLine();
        validator.validateCointStorage(inputMoney, "자판기 보유 금액");
        return CoinStorage.fromMoney(Converter.convertToInt(inputMoney));
    }

//    public Products inputProducts() {
//        printer.printLine("");
//        String template = reader.readLine();
//        validator.validate(template, "템플릿");
//        return new Template();
//    }

//    public Template inputTemplate() {
//        printer.printLine("");
//        String template = reader.readLine();
//        validator.validate(template, "템플릿");
//        return new Template();
//    }
}
