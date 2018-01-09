package com.josalero.webreactive;

import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@EnableReactiveMongoRepositories(basePackages= {"com.josalero.webreactive"})
public class MongoConfig extends AbstractReactiveMongoConfiguration {


	@Override
	protected String getDatabaseName() {
		return "articles";
	}

	@Override
	public MongoClient reactiveMongoClient() {
		
		return MongoClients.create();
	}
}
