package com.github.promotion.storage;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author supportteam
 *
 * Contains CRUD methods to Mongo databse server.
 */
@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {

	/**
	 * Find valid promotions from database by valid date. 
	 * A valid promotion is its validFrom <= validDate and validDate <= validUntil.
	 * @param validDate  
	 * @return promotions that matched with above criteria.
	 */
	@Query("{'$and' : [{'validFrom' : {$lt : ?0}}, {'validUntil' : {$gt : ?0}}]}")
	public List<Promotion> findByValidDate(Date validDate);
}