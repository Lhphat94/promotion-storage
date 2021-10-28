package com.github.promotion.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.github.promotion.storage.Promotion;
import com.github.promotion.storage.PromotionType;

public class PromotionDataHelper {

	public static List<Promotion> generatePromotionTestData() {
        List<Promotion> promotions = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 6, 8, 9, 9, 9);
        Date dateFrom = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date dateTo = calendar.getTime();
        Promotion promotion = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo, 35, "Floating Market Festival, Discount 35% for CanTho citizens");
        Promotion promotion1 = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, dateTo,  21, "July 7 Week, Discount 21% for pinky T-shirt men");
        calendar = Calendar.getInstance();

        LocalDate currentDate = LocalDate.now().plusDays(1);

        calendar.set(Calendar.MONTH, currentDate.getMonthValue());
        calendar.set(Calendar.DAY_OF_MONTH, currentDate.getDayOfMonth());
        
        Promotion promotion2 = new Promotion(PromotionType.MOVIE, dateFrom, calendar.getTime(), 600, "Go in group of 7, Buy 1 Get 6 Free");
        Promotion promotion3 = new Promotion(PromotionType.FOOD_AND_DRINK, dateFrom, calendar.getTime(), 11, "Uong Tra Sua Fucklone hem");

        promotions.add(promotion);
        promotions.add(promotion1);
        promotions.add(promotion2);
        promotions.add(promotion3);

        return promotions;
    }
}
