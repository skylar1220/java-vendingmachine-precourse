package vendingmachine.util.validator;

import java.util.HashSet;
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

    public static void validateStartsWith(String separator, String value, String target) {
        if (value.startsWith(separator)) {
            throw new IllegalArgumentException(String.format("%s은(는) 구분자로 시작할 수 없습니다.", target));
        }
    }

    public static void validateEndsWith(String separator, String value, String target) {
        if (value.endsWith(separator)) {
            throw new IllegalArgumentException(String.format("%s은(는) 구분자로 끝날 수 없습니다.", target));
        }
    }

    public static void validateListForDuplicates(List<String> values, String message) {
        if (hasDuplicates(values)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateMinimumSplitCount(String seperator, String value, int minSplittedCount, String target) {
        List<String> values = Converter.splitToList(seperator, value);
        if (!hasOverMinCount(values, minSplittedCount)) {
            throw new IllegalArgumentException(String.format("%s은(는) 최소 %d개 이상이여야 합니다..", target, minSplittedCount));
        }
    }

    public static void validateSplitCount(String seperator, String value, int requiredCount, String target) {
        List<String> values = Converter.splitToList(seperator, value);
        if (!hasValidCount(values, requiredCount)) {
            throw new IllegalArgumentException(String.format("%s은(는) 올바르게 하나씩 입력해주세요", target));
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

    private static boolean hasDuplicates(List<String> values) {
        return new HashSet<>(values).size() != values.size();
    }

    private static boolean hasOverMinCount(List<String> values, int minSplittedCount) {
        return values.size() >= minSplittedCount;
    }

    private static boolean hasValidCount(List<String> values, int requiredCount) {
        return values.size() == requiredCount;
    }
}
