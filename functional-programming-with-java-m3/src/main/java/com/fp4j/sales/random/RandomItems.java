package com.fp4j.sales.random;

import com.fp4j.sales.Item;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.fp4j.sales.random.RandomUtil.randomElement;

public class RandomItems {

    private static final double MAX_PRICE = 150.00;
    private static final String[] AVAILABLE_ITEMS = new String[]{
            "carrot", "eggs", "lizard", "cookie", "pickle", "cow", "rug"};
    private Random random = new Random();
    private Supplier<Item> itemSupplier = () ->
            new Item(pickAnIdentity(), pickAPrice());

    public static Stream<Item> infiniteStream() {
        return Stream.generate(new RandomItems().itemSupplier);
    }

    private Double pickAPrice() {
        return Math.round(random.nextDouble() * MAX_PRICE * 100) / 100.0; // might be free
    }

    private String pickAnIdentity() {
        return randomElement(AVAILABLE_ITEMS);
    }
}
