package com.fp4j.collections;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class ExampleConvertedFromGuava {

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
        return Arrays.stream(descriptions)
                .peek(s -> System.out.println("About "))
                .filter(s -> !s.isEmpty()) // skip some
                .map(lastWord) // transform
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
