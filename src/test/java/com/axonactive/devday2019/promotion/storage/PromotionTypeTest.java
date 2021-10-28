package com.github.promotion.storage;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;

public class PromotionTypeTest {

	@Test
	public void should_return_Coffee_when_COFFEE_enum_is_given() {
		PromotionType type = PromotionType.BOOK;
		assertThat(type.getName(), Is.is("Book"));
		
		type = PromotionType.FOOD_AND_DRINK;
		assertThat(type.getName(), Is.is("Food and Drink"));
	}
}
