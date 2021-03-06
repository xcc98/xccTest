package com.xcc.demo.test.service.java8;

import com.xcc.demo.test.dao.Item;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorTest3 {
    public static void main(String[] args) {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
       
        Map<String, Map<BigDecimal, List<Item>>> invokeSourceSystemListMap = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.groupingBy(Item::getPrice))
        );

        System.out.println(invokeSourceSystemListMap);
    }
}
