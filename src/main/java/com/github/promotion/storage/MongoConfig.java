package com.github.promotion.storage;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * @author supportteam
 * 
 * The class setups configuration to connect to Mongo database server.
 *
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Value("${promotion.storage.mongodb.uri}")
	private String mongodbUri;
	
	@Value("${promotion.storage.mongodb.dbname}")
	private String mongodbName;
	
    @Override
    public MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI(this.mongodbUri);
        return new MongoClient(uri);
    }

    @Override
    protected String getDatabaseName() {
        return this.mongodbName;
    }
}
