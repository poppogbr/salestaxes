package it.salestaxes;

import it.salestaxes.helper.CreateProductHelper;
import it.salestaxes.helper.ProductCalculatorHelper;
import it.salestaxes.shopping.Cart;
import it.salestaxes.shopping.product.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class MainTest {

    @Test
    public void testReadFile() throws Exception {
        FileScanner fileScanner = new FileScanner("input1.txt");
        List<String> lines = fileScanner.lines();
        Assert.assertNotNull(lines);
        Assert.assertEquals("1 book at 12.49", lines.get(0));
        Assert.assertEquals("1 music CD at 14.99", lines.get(1));
        Assert.assertEquals("1 chocolate bar at 0.85", lines.get(2));
    }

    @Test
    public void testCreateProduct() throws Exception {
        FileScanner fileScanner = new FileScanner("input1.txt");
        List<Product> productList = CreateProductHelper.createProductListFromLines(fileScanner);
        Assert.assertNotNull(productList);
        Assert.assertEquals(3, productList.size());
        Assert.assertEquals(false, productList.get(0).isImported());
        Assert.assertEquals(new BigDecimal("12.49"), productList.get(0).getPrice());
        Assert.assertEquals(1, productList.get(0).getQuantity());
        Assert.assertEquals(null, productList.get(0).getTaxedPrice());
        Assert.assertEquals(new Integer(0), productList.get(0).getTaxValue());
        Assert.assertEquals("book", productList.get(0).getDescription());

        Assert.assertEquals(new Integer(10), productList.get(1).getTaxValue());
    }

    @Test
    public void testCreateProductImported() throws Exception {
        FileScanner fileScanner = new FileScanner("input2.txt");
        List<Product> productList = CreateProductHelper.createProductListFromLines(fileScanner);
        Assert.assertNotNull(productList);
        Assert.assertEquals(2, productList.size());
        Assert.assertEquals(true, productList.get(0).isImported());
        Assert.assertEquals(new BigDecimal("10.00"), productList.get(0).getPrice());
        Assert.assertEquals(1, productList.get(0).getQuantity());
        Assert.assertEquals(null, productList.get(0).getTaxedPrice());
        Assert.assertEquals(new Integer(0), productList.get(0).getTaxValue());
        Assert.assertEquals("imported box of chocolates", productList.get(0).getDescription());

        Assert.assertEquals(new Integer(10), productList.get(1).getTaxValue());
        Assert.assertEquals(true, productList.get(1).isImported());
    }

    @Test
    public void testCalculateTaxImported() throws Exception {
        FileScanner fileScanner = new FileScanner("input2.txt");
        List<Product> productList2 = CreateProductHelper.createProductListFromLines(fileScanner);

        Assert.assertEquals(new BigDecimal("0.50"),
                ProductCalculatorHelper.calculateProductTax(productList2.get(0)));
        Assert.assertEquals(new BigDecimal("7.15"),
                ProductCalculatorHelper.calculateProductTax(productList2.get(1)));
    }

    @Test
    public void testCalculateTax() throws Exception {
        FileScanner fileScanner = new FileScanner("input1.txt");
        List<Product> productList = CreateProductHelper.createProductListFromLines(fileScanner);

        Assert.assertEquals(new BigDecimal("0.00"),
                ProductCalculatorHelper.calculateProductTax(productList.get(0)));
        Assert.assertEquals(new BigDecimal("1.50"),
                ProductCalculatorHelper.calculateProductTax(productList.get(1)));
    }

    @Test
    public void testCalculateTotalCost() throws Exception {
        FileScanner fileScanner = new FileScanner("input2.txt");
        List<Product> productList = CreateProductHelper.createProductListFromLines(fileScanner);

        Assert.assertEquals(new BigDecimal("10.50"),
                ProductCalculatorHelper.calculateTotalProductCost(productList.get(0)));
        Assert.assertEquals(new BigDecimal("54.65"),
                ProductCalculatorHelper.calculateTotalProductCost(productList.get(1)));
    }

    @Test
    public void testPrepareReceipt() throws Exception {
        FileScanner fileScanner = new FileScanner("input3.txt");
        List<Product> productList = CreateProductHelper.createProductListFromLines(fileScanner);
        Cart cart = new Cart(productList);
        List<String> receipt = cart.prepareReceipt();

        Assert.assertEquals("1 imported bottle of perfume: 32.19", receipt.get(0));
        Assert.assertEquals("1 bottle of perfume: 20.89", receipt.get(1));
        Assert.assertEquals("1 packet of headache pills: 9.75", receipt.get(2));
        Assert.assertEquals("1 box of imported chocolates: 11.85", receipt.get(3));
        Assert.assertEquals("Sales Taxes: 6.70", receipt.get(4));
        Assert.assertEquals("Total: 74.68", receipt.get(5));
    }

}
