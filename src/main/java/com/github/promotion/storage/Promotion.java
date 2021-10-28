package com.github.promotion.storage;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity to store the promotion information and return to the GUI
 * */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter @Setter
@NoArgsConstructor
@Document("promotion")
public class Promotion implements Serializable {

	/**
	 * promotion code. unique
	 */
	@Id
	private String code;
	
	/**
	 * promotion type (COFFEE, TEA, COMPUTER,...)
	 */
	private PromotionType type;

	/**
	 * created date of promotion
	 * */
	private Date validFrom;

	/**
	 * expired day of promotion
	 * */
	private Date validUntil;
	
	/**
	 * discount percentage of the promotion
	 */
	private int discountPercentage;

	/**
	 * details about the promotion program
	 * */
	private String description;

	/**
	 * Constructor to create a promotion with full information.
	 * @param type  promotion type (COFFEE, TEA, COMPUTER,...)
	 * @param validFrom  created date of promotion
	 * @param validUntil  expired day of promotion
	 * @param discountPercentage  discount percentage of the promotion
	 * @param description  details about the promotion program
	 */
	public Promotion(PromotionType type, Date validFrom, Date validUntil, int discountPercentage, String description) {
		this.type = type;
		this.validFrom = validFrom;
		this.validUntil = validUntil;
		this.discountPercentage = discountPercentage;
		this.description = description;
	}
	
}

