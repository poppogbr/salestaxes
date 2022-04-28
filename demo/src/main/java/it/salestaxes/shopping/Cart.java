package it.salestaxes.shopping;

import it.salestaxes.helper.ProductCalculatorHelper;
import it.salestaxes.shopping.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private List<Product> products = new ArrayList<>();
    private BigDecimal totalTaxes = new BigDecimal(0);;
    private BigDecimal totalAmount = new BigDecimal(0);

    public Cart() {
    }

    public Cart(List<Product> products) {
        this.products = (Objects.nonNull(products)) ? products : new ArrayList<>();
    }

    public List<String> prepareReceipt() {
        List<String> receipt = new ArrayList<>();

        for(Product p : products) {
            p.setTaxedPrice(ProductCalculatorHelper.calculateTotalProductCost(p));
            totalTaxes = totalTaxes.add(ProductCalculatorHelper.calculateProductTax(p));
            totalAmount = totalAmount.add(p.getTaxedPrice());
            receipt.add(p.toString());
        }

        receipt.add("Sales Taxes: "+ totalTaxes);
        receipt.add("Total: "+ totalAmount);

        return receipt;
    }
}
