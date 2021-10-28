package com.github.promotion.storage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author supportteam
 *
 * Contains pre-defined promotion types.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PromotionType {

	FOOD_AND_DRINK("Food and Drink"),
    MOVIE("Movie"),
    ELECTRONIC_DEVICE("Electronic Device"),
    BOOK("Book"),
    VEHICLE("Vehicle"),
    SPORT_AND_BEAUTY("Sport and Beauty");

    @Getter
    private String name;
}
