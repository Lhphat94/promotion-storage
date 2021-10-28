package com.github.promotion.storage;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 
 * @author supportteam
 *
 * Contains CRUD methods to Mongo databse server.
 */
@Service
public class PromotionService {
	private static final Logger LOGGER = LogManager.getLogger(PromotionService.class);

	@Autowired
	private PromotionRepository promotionRepository;

	/**
	 * Add new promotion to database.
	 * @param promotion  contains promotion data.
	 */
	public Promotion add(Promotion promotion) {
		validatePromotion(promotion);
		promotion = promotionRepository.insert(promotion);
		LOGGER.info("Added promotion '{}' successfully", promotion.getCode());
		return promotion;
	}

	/**
	 * Update existing promotion to database by promotion code.
	 * @param code  the promotion code
	 * @param promotion  contains updated promotion data.
	 */
	public Promotion update(String promotionCode, Promotion newPromotion) {
		validatePromotionCode(promotionCode);
		validatePromotion(newPromotion);
		Optional<Promotion> existingOne = this.promotionRepository.findById(promotionCode);
		
		if (existingOne.isPresent()) {
			Promotion udpdatedOne = existingOne.get();
			udpdatedOne.setDescription(newPromotion.getDescription());
			udpdatedOne.setDiscountPercentage(newPromotion.getDiscountPercentage());
			udpdatedOne.setType(newPromotion.getType());
			udpdatedOne.setValidFrom(newPromotion.getValidFrom());
			udpdatedOne.setValidUntil(newPromotion.getValidUntil());
			udpdatedOne = this.promotionRepository.save(udpdatedOne);
			LOGGER.info("Updated promotion '{}' successfully", udpdatedOne.getCode());
			return udpdatedOne;
		}
		
		else {
			IllegalArgumentException ex = new IllegalArgumentException("Promotion not found for code " + promotionCode);
			LOGGER.error("Updated promotion '{}' failed. ", promotionCode, ex);
			throw ex;
		}
	}

	/**
	 * Retrieve all existing promotions from database.
	 * @return  all existing promotions from database.
	 */
	public List<Promotion> getAll() {
		List<Promotion> promotions = promotionRepository.findAll();
		LOGGER.info("Loaded {} promotions successfully", promotions.size());
		return promotions;
	}

	/**
	 * Retrieve valid promotions from database.
	 * A valid promotion is its validFrom <= validDate and validDate <= validUntil.
	 * @param validDate
	 * @return  valid promotions that matched with above criteria.
	 */
	public List<Promotion> getValidPromotions(Date validDate) {
		List<Promotion> promotions = promotionRepository.findByValidDate(validDate);
		LOGGER.info("Loaded {} valid promotions successfully", promotions.size());
		for(Promotion promotion : promotions) {
			LOGGER.info("Description = {}", promotion.getDescription());
		}
		return promotions;
	}

	/**
	 * Validate whether the given promotion is null or not.
	 * @param promotion
	 */
	private void validatePromotion(Promotion promotion) {
		if (Objects.isNull(promotion)) {
			throw new IllegalArgumentException("Promotion is missing");
		}
	}
	
	/**
	 * Validate whether the given promotion code is null or not.
	 * @param promotionCode
	 */
	private void validatePromotionCode(String promotionCode) {
		if (StringUtils.isEmpty(promotionCode)) {
			throw new IllegalArgumentException("Promotion code is missing");
		}
	}
}
