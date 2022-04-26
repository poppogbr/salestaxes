package com.salestaxes.helper;

import com.salestaxes.shopping.product.Product;

import java.math.BigDecimal;

public class ProductCalculatorHelper {

    public static BigDecimal calculateProductTax(Product product) {
        BigDecimal tax = RoundedSalesTax.getTaxAmount(product.getPrice(), product.getTaxValue());

        if (product.isImported()) {
            return tax.add(RoundedSalesTax.getTaxAmount(product.getPrice(), 5));
        }
        return tax;
    }

    public static BigDecimal calculateTotalProductCost(Product product) {
        return product.getPrice().add(calculateProductTax(product));
    }
}
