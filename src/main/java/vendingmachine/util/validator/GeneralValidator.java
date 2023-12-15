package vendingmachine.util.validator;

import java.util.List;
import vendingmachine.util.converter.Converter;

public class GeneralValidator {
    private GeneralValidator() {
    }

    public static void validateSingleSeparator(String separator, String value, String target) {
        if (containsDuplicateSubstring(separator, value)) {
            throw new IllegalArgumentException(String.format("%s에 구분자는 하나만 입력해주세요", target));
        }
    }

    public static void validateStartsWith(String subString, String value, String target) {
        if (!value.startsWith(subString)) {
            throw new IllegalArgumentException(String.format("%s은(는) %s로 시작해야 합니다.", target, subString));
        }
    }

    public static void validateEndsWith(String subString, String value, String target) {
        if (!value.endsWith(subString)) {
            throw new IllegalArgumentException(String.format("%s은(는) %s로 끝나야 합니다.", target, subString));
        }
    }
    public static void validateNotStartsWith(String subString, String value, String target) {
        if (value.startsWith(subString)) {
            throw new IllegalArgumentException(String.format("%s은(는) %s로 시작할 수 없습니다.", target, subString));
        }
    }

    public static void validateNotEndsWith(String subString, String value, String target) {
        if (value.endsWith(subString)) {
            throw new IllegalArgumentException(String.format("%s은(는) %s로 끝날 수 없습니다.", target, subString));
        }
    }

    public static void validateSplitCount(String seperator, String value, int requiredCount, String target) {
        List<String> values = Converter.splitToList(seperator, value);
        if (!hasValidCount(values, requiredCount)) {
            throw new IllegalArgumentException(String.format("%s은(는) 입력 형식이 올바르지 않습니다.", target));
        }
    }

    public static void validateCount(List<String> values, int requiredCount, String target) {
        if (hasValidCount(values, requiredCount)) {
            throw new IllegalArgumentException(String.format("%s은(는) 입력 형식이 올바르지 않습니다.", target));
        }
    }

    private static boolean containsDuplicateSubstring(String substring, String value) {
        String doubleSubstring = substring + substring;
        return value.contains(doubleSubstring);
    }

    private static boolean hasValidCount(List<String> values, int requiredCount) {
        return values.size() == requiredCount;
    }
}
