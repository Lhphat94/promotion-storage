package com.github.promotion.storage;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.promotion.storage.Promotion;
import com.github.promotion.storage.PromotionRepository;
import com.github.promotion.storage.PromotionService;
import com.github.promotion.storage.PromotionType;


@RunWith(SpringRunner.class)
public class PromotionServiceTest {
	
	@MockBean
	private PromotionRepository promotionRepository;
	
	@Autowired
	private PromotionService promotionService;
	
	@TestConfiguration
	static class LAUSUtilityServiceTestConfiguration{
		@Bean
		public PromotionService promotionService() {
			return new PromotionService();
		}
	}

	@Test
	public void should_insert_new_promotion_into_mongodb_when_valid_promotion_is_given() {
		Calendar calendar = Calendar.getInstance();
    	calendar.set(2019, 6, 8, 9, 9, 9);
    	Date dateFrom = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7);
    	Date dateTo = calendar.getTime();
    	Promotion promotion = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo, 35, "Floating Market Festival, Discount 35% for CanTho citizens");
    	
    	Promotion mockPromotion = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo, 35, "Floating Market Festival, Discount 35% for CanTho citizens");
    	mockPromotion.setCode("P1");
    	
    	Mockito.when(promotionRepository.insert(promotion)).thenReturn(mockPromotion);
    	
    	Promotion insertedPromotion = this.promotionService.add(promotion);
    	
    	assertThat(insertedPromotion.getCode(), Is.is("P1"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void should_throw_IllegalArgumentException_when_inserted_promotion_is_null() {
    	Promotion promotion = null;
    	
    	this.promotionService.add(promotion);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void should_throw_IllegalArgumentException_when_updated_promotion_code_is_null() {
		Calendar calendar = Calendar.getInstance();
    	calendar.set(2019, 6, 8, 9, 9, 9);
    	Date dateFrom = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7);
    	Date dateTo = calendar.getTime();
    	Promotion promotion = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo, 35, "Floating Market Festival, Discount 35% for CanTho citizens");
    	
    	String promotionCode = null;
    	
    	this.promotionService.update(promotionCode, promotion);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void should_throw_IllegalArgumentException_when_updated_promotion_code_is_not_exist() {
		Calendar calendar = Calendar.getInstance();
    	calendar.set(2019, 6, 8, 9, 9, 9);
    	Date dateFrom = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7);
    	Date dateTo = calendar.getTime();
    	Promotion promotion = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo, 35, "Floating Market Festival, Discount 35% for CanTho citizens");
    	
    	String promotionCode = "non-existing-code";
    	
    	this.promotionService.update(promotionCode, promotion);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void should_throw_IllegalArgumentException_when_updated_promotion_is_null() {
    	Promotion promotion = null;
    	
    	String promotionCode = "P1";
    	
    	this.promotionService.update(promotionCode, promotion);
	}
	
	@Test
	public void should_update_promotion_into_mongodb_when_valid_promotion_and_code_are_given() {
		Calendar calendar = Calendar.getInstance();
    	calendar.set(2019, 6, 8, 9, 9, 9);
    	Date dateFrom = calendar.getTime();
    	calendar.add(Calendar.DAY_OF_MONTH, 7);
    	Date dateTo = calendar.getTime();

    	Promotion mockPromotion = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo, 35, "Floating Market Festival, Discount 35% for CanTho citizens");
    	mockPromotion.setCode("P1");
    	
    	Optional<Promotion> mockPromotionOptional = Optional.of(mockPromotion);
    	String promotionCode = "P1";
    	
    	Mockito.when(this.promotionRepository.findById(promotionCode)).thenReturn(mockPromotionOptional);
    	
    	Promotion updatedPromotion = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo, 20, "Floating Market Festival, Update Discount 20% for CanTho citizens");
    	updatedPromotion.setCode("P1");
    	Mockito.when(promotionRepository.save(mockPromotionOptional.get())).thenReturn(updatedPromotion);
    	
    	
    	Promotion afterUpdatedPromotion = this.promotionService.update(promotionCode, updatedPromotion);
    	
    	assertThat(afterUpdatedPromotion.getDiscountPercentage(), Is.is(20));
    	assertThat(afterUpdatedPromotion.getDescription(), Is.is("Floating Market Festival, Update Discount 20% for CanTho citizens"));
	}
	
	@Test
	public void should_return_all_promotions_from_database() {
		List<Promotion> promotions = PromotionDataHelper.generatePromotionTestData();
		Mockito.when(this.promotionRepository.findAll()).thenReturn(promotions);
		
		List<Promotion> returnedPromotions = this.promotionService.getAll();
		
		assertThat(returnedPromotions.size(), Is.is(4));
	}
	
	@Test
	public void should_return_valid_promotions_from_database() {
		Date today = new Date();
		List<Promotion> promotions = PromotionDataHelper.generatePromotionTestData();
		Mockito.when(this.promotionRepository.findByValidDate(today)).thenReturn(promotions);
		
		List<Promotion> returnedPromotions = this.promotionService.getValidPromotions(today);
		assertThat(returnedPromotions.size(), Is.is(4));
	}

}
