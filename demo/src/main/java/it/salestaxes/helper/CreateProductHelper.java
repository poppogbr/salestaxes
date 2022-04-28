package it.salestaxes.helper;

import it.salestaxes.shopping.product.Product;
import it.salestaxes.shopping.product.factory.ProductFactory;
import it.salestaxes.FileScanner;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CreateProductHelper {

    private static final String LINE_FORMAT = "(?<quantity>\\d*) (?<description>[\\w\\s]*) at (?<price>\\d*\\.\\d*)";
    private static final String IMPORTED = "imported";

    public static List<Product> createProductListFromLines(FileScanner scanner) throws Exception {
        return scanner.lines().stream()
                .map(line -> {
                    Pattern pattern = Pattern.compile(LINE_FORMAT);
                    Matcher matcher = pattern.matcher(line);
                    matcher.matches();
                    return matcher;
                })
                .map(matcher -> newProductInstance(matcher))
                .collect(Collectors.toList());
    }

    private static Product newProductInstance(Matcher matcher) {
        String description = matcher.group("description");
        return ProductFactory.createProduct(description,
                new BigDecimal(matcher.group("price")),
                description.contains(IMPORTED),
                Integer.parseInt(matcher.group("quantity"))
        );
    }
}
