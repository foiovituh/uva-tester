package com.github.foiovituh.uvatester.utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import static java.util.stream.Collectors.toSet;

public final class Assertor {
    private Assertor() {
    }
    
    public static boolean allElementsAreSame(List<?> list) {
        return !isNullOrEmpty(list)
                && 1 == list.stream()
                        .collect(toSet())
                        .size();
    }

    public static boolean areCollectionsEqual(Collection<?> first,
            Collection<?> second) {
        return Objects.equals(first, second);
    }

    public static boolean containsNull(Collection<?> collection) {
        return collection != null && collection.contains(null);
    }

    public static boolean hasSize(List<?> list, int size) {
        return list != null && list.size() == size;
    }

    public static boolean isNotNullOrBlank(String string) {
        return string != null && !string.isBlank();
    }
    
    public static boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
    
    public static boolean isTrue(Boolean booleanToCompare) {
        if (booleanToCompare == null) {
            return false;
        }
        
        return Objects.equals(Boolean.TRUE, booleanToCompare);
    }

    public static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
