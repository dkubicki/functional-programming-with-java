package com.fp4j.collections;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CollectionsExample {

    public static final Predicate<String> NON_EMPTY = s -> !s.isEmpty();
    final static String[] food = new String[]{
            "Crunchy carrots",
            "Golden-hued bananas",
            "",
            "Bright orange pumpkins",
            "Little trees of broccoli",
            "meat"
    };
    private static BinaryOperator<String> last =
            (other, last) -> last;
    private static Function<String, String> lastWord = (String phrase) ->
            Arrays.stream(phrase.split(" "))
                    .reduce(last)
                    .orElse("");

    private static String summarize(String[] descriptions) {
        System.out.println("---Setting up the stream");
        Stream<String> lastWords = Arrays.stream(descriptions)
                .peek(s -> System.out.println("About to filter: " + s))
                .filter(NON_EMPTY)
                .limit(3)
                .peek(s -> System.out.println("About to map: " + s))
                .map(lastWord)
                .peek(s -> System.out.println("About to reduce: " + s));

        System.out.println("---Reducing");
        return lastWords
                .reduce(joinOn(" & "))
                .orElse("");
    }

    private static BinaryOperator<String> joinOn(String divider) {
        return (allSoFar, oneMore) ->
                allSoFar + divider + oneMore;
    }

    public static void main(String[] args) {
        final String summary = summarize(food);

        final String desired = "carrots & bananas & pumpkins & broccoli & meat";
        System.out.println(summary);
        if (summary.equals(desired)) System.out.println("yay!");
    }
}
