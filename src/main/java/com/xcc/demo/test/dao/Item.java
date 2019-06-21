package com.xcc.demo.test.dao;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private String name;
    private int qty;
    private BigDecimal price;

    public Item(String name,int qty,BigDecimal price){
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
