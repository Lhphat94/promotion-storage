package com.github.promotion.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author supportteam
 *
 * Provide rest api to allow client to perform CRUD actions on promotion.
 */
@RestController
@RequestMapping("/promotions")
public class PromotionRestController {
	
	@Autowired(required=true)
    private PromotionService promotionService;
	
	/**
	 * Test whether the service is reachable via HTTP or not
	 * @return
	 */
	@GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
	
	/**
	 * Add new promotion to database.
	 * @param promotion  contains promotion data.
	 */
	@PostMapping
	public void add(@RequestBody Promotion promotion){
		promotionService.add(promotion);
	}
	
	/**
	 * Update existing promotion to database by promotion code.
	 * @param code  the promotion code
	 * @param promotion  contains updated promotion data.
	 */
	@PutMapping("/{code}")
	public void update(@PathVariable(name = "code") String code, 
			@RequestBody Promotion newPromotion){
		promotionService.update(code, newPromotion);
	}

	/**
	 * Retrieve all existing promotions from database.
	 * @return  all existing promotions from database.
	 */
	@GetMapping
	public ResponseEntity<List<Promotion>> getAll(){
		List<Promotion> promotions = new ArrayList<>();
		try{
			promotions = promotionService.getAll();
		}catch(DataAccessException ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
		}
		return ResponseEntity.ok(promotions);

	}
	
	/**
	 * Retrieve valid promotions from database.
	 * A valid promotion is its validFrom <= validDate and validDate <= validUntil.
	 * @param validDate
	 * @return  valid promotions that matched with above criteria.
	 */
	@GetMapping("/valid")
	public ResponseEntity<List<Promotion>> getValidPromotions(@RequestParam("validDate") Date validDate){
		List<Promotion> promotions = new ArrayList<>();
		try{
			promotions = promotionService.getValidPromotions(validDate);
		}catch(DataAccessException ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
		}
		return ResponseEntity.ok(promotions);
	}
}
