/**
 * 
 */
package com.josalero.webreactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.josalero.webreactive.entity.Article;


/**
 * @author josealeman
 *
 */

public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {
    

}