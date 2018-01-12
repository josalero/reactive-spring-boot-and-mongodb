/**
 * 
 */
package com.josalero.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.josalero.reactive.entity.Article;


/**
 * @author josealeman
 *
 */

public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {
    

}