package com.salestaxes.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundedSalesTax {

	private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

	private static final BigDecimal ROUNDING = new BigDecimal("0.05");

	public static BigDecimal getTaxAmount(BigDecimal price, Integer increment) {
		return round(price.multiply(new BigDecimal(increment)).divide(ONE_HUNDRED));
	}

	private static BigDecimal round(BigDecimal price) {
		return round(price, ROUNDING, RoundingMode.UP);
	}

	private static BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
		if (increment.signum() == 0) {
			return value;
		}
		return value.divide(increment, 0, roundingMode).multiply(increment);
	}

}