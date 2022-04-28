package it.salestaxes.shopping.product.factory;

import it.salestaxes.shopping.product.Product;
import it.salestaxes.shopping.product.TaxFreeProduct;
import it.salestaxes.shopping.product.Taxed10Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProductFactory {

    private static final List<String> SPECIAL_CATEGORIES = Arrays.asList("book", "chocolate", "pill");

    public static Product createProduct(String description, BigDecimal price, boolean imported, int quantity) {

        if(SPECIAL_CATEGORIES.stream().anyMatch(sc -> description.contains(sc))) {
            return new TaxFreeProduct(description, price, imported, quantity);
        } else {
            return new Taxed10Product(description, price, imported, quantity);
        }
    }
}
