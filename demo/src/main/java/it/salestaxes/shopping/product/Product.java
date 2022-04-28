package it.salestaxes.shopping.product;

import java.math.BigDecimal;

public abstract class Product {

    protected String description;
    protected BigDecimal price;
    protected Boolean imported;
    protected Integer quantity;
    protected BigDecimal taxedPrice;

    public Product() {
        this.description = "";
        this.price = new BigDecimal("0.0");
        this.imported = false;
        this.quantity = 0;
        this.taxedPrice = new BigDecimal("0.0");
    }

    public Product(String description, BigDecimal price, boolean imported, int quantity) {
        this.description = description;
        this.price = price.multiply(BigDecimal.valueOf(quantity));
        this.imported = imported;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.multiply(BigDecimal.valueOf(quantity));
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTaxedPrice() {
        return taxedPrice;
    }

    public void setTaxedPrice(BigDecimal taxedPrice) {
        this.taxedPrice = taxedPrice;
    }

    public abstract Integer getTaxValue();

    @Override
    public String toString() {
        return quantity + " " + description + ": " + taxedPrice;
    }
}