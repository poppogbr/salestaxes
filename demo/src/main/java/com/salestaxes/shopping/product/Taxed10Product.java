package com.salestaxes.shopping.product;

import java.math.BigDecimal;

public class Taxed10Product extends Product {

    public Taxed10Product() {
        super();
    }

    public Taxed10Product(String description, BigDecimal price, boolean imported, int quantity) {
        super(description, price, imported, quantity);
    }

    @Override
    public Integer getTaxValue() {
        return new Integer(10);
    }
}