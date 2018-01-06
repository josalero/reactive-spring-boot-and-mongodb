/**
 * 
 */
package com.josalero.webreactive.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josalero.webreactive.entity.Article;
import com.josalero.webreactive.repository.ArticleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author josealeman
 *
 */
@RestController
public class ArticleController {

	private final ArticleRepository articleRepository;
	
	@Autowired
	public ArticleController(final ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@GetMapping("/articles")
	public Flux<Article> getAll(){
		return articleRepository.findAll();
	}
	
	@PostMapping("/articles")
	public Mono<Article> postArticle(){
		//Mono<Article> article = Mono.;
		 return articleRepository.save(new Article("Article " + UUID.randomUUID().toString(), "Text " + UUID.randomUUID().toString()));
	}
}
