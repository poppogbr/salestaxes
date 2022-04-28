package it.salestaxes;

import it.salestaxes.helper.CreateProductHelper;
import it.salestaxes.shopping.Cart;
import it.salestaxes.shopping.product.Product;

import java.util.List;

public class SalesTaxesApplication {

	public static void main(String[] args) throws Exception {
		FileScanner fileScanner = new FileScanner("input1.txt");
		List<Product> products = CreateProductHelper.createProductListFromLines(fileScanner);
		Cart cart = new Cart(products);
		cart.prepareReceipt().forEach(l -> System.out.println(l));

		FileScanner fileScanner2 = new FileScanner("input2.txt");
		List<Product> products2 = CreateProductHelper.createProductListFromLines(fileScanner2);
		Cart cart2 = new Cart(products2);
		cart2.prepareReceipt().forEach(l -> System.out.println(l));

		FileScanner fileScanner3 = new FileScanner("input3.txt");
		List<Product> products3 = CreateProductHelper.createProductListFromLines(fileScanner3);
		Cart cart3 = new Cart(products3);
		cart3.prepareReceipt().forEach(l -> System.out.println(l));
	}

}
