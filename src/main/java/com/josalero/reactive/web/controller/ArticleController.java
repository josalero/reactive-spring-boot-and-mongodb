/**
 * 
 */
package com.josalero.reactive.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josalero.reactive.entity.Article;
import com.josalero.reactive.repository.ArticleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author josealeman
 *
 */
@RestController
@RequestMapping(value="/rest/articles")
public class ArticleController {

	private final ArticleRepository articleRepository;
	
	@Autowired
	public ArticleController(final ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@GetMapping
	public Flux<Article> getAll(){
		return articleRepository.findAll();
	}
	
	@PostMapping
	public Mono<ResponseEntity<Article>> postArticle(){
		//Mono<Article> article = Mono.;
		 return articleRepository.save(new Article("Article " + UUID.randomUUID().toString(), "Text " + UUID.randomUUID().toString()))
				 				.log()
				 				.map(article -> new ResponseEntity<Article>(article, HttpStatus.CREATED));
	}
}
