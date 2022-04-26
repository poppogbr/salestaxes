package com.salestaxes.shopping.product;

import java.math.BigDecimal;

public class TaxFreeProduct extends Product {

    public TaxFreeProduct() {
        super();
    }

    public TaxFreeProduct(String name, BigDecimal price, boolean imported, int quantity) {
        super(name, price, imported, quantity);
    }

    @Override
    public Integer getTaxValue() {
        return new Integer(0);
    }
}