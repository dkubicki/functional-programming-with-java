package com.fp4j.sales.random;


import com.fp4j.sales.Item;
import com.fp4j.sales.Sale;
import com.fp4j.sales.Store;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.fp4j.sales.random.RandomUtil.randomElement;

public class RandomSale {


    private static final int MAX_ITEMS = 6;
    private static final Double PERCENT_NO_CUSTOMER = 0.25;
    private static final String[] CUSTOMERS =
            new String[]{"Wilma", "Betty", "Fred", "Barney", "Dino"};
    private static Random random = new Random();
    public static Supplier<Sale> supplier = () -> new Sale(
            pickAStore(),
            new Date(),
            pickACustomer(),
            randomListOfItems());

    public static Stream<Sale> streamOf(long qty) {
        return Stream.generate(supplier).limit(qty);
    }

    private static List<Item> randomListOfItems() {
        int howMany = random.nextInt(MAX_ITEMS - 1) + 1;
        return RandomItems.infiniteStream()
                .limit(howMany)
                .collect(Collectors.toList());
    }

    private static Optional<String> pickACustomer() {
        if (random.nextDouble() < PERCENT_NO_CUSTOMER) {
            return Optional.empty();
        } else {
            return Optional.of(randomElement(CUSTOMERS));
        }
    }


    private static Store pickAStore() {
        return randomElement(Store.values());
    }
}
