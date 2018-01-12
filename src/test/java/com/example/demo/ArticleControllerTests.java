package com.example.demo;


import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.josalero.reactive.entity.Article;
import com.josalero.reactive.repository.ArticleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class ArticleControllerTests extends WebReactiveSpringBootMongoDbApplicationTests{

	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ArticleRepository articleRepository;
	
	Flux<Article> articleFlux;

	public ArticleControllerTests() {

	}

	@Before
	public void setup() {
		Article article1 = new Article("Uno", "Uno Desc");
		Article article2 = new Article("Dos", "Dos Desc");
		Article article3 = new Article("Tres", "Tres Desc");
		
		articleFlux = Flux.just(article1, article2, article3);

	}
	@Test
	public void listArticlesUsingRest() {

		BDDMockito.given(this.articleRepository.findAll()).willReturn(articleFlux);

		webTestClient.get()
			.uri("/rest/articles")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectBodyList(Article.class).hasSize(3);

	}
	
	@Test
	public void postArticlesUsingRest() {
		Article article1 = new Article("1", "Uno", "Uno Desc");
		BDDMockito.given(this.articleRepository.save(BDDMockito.any())).willReturn(Mono.just(article1));

		webTestClient.mutate().filter(basicAuthentication("user", "password")).build()
			.post()
			.uri("/rest/articles")
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.CREATED);

	}
	
	@Test
	public void listArticlesUsingHandler() {

		BDDMockito.given(this.articleRepository.findAll()).willReturn(articleFlux);

		webTestClient.get()
			.uri("/api/articles")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectBodyList(Article.class).hasSize(3);

	}
	
	@Test
	public void postArticlesUsingHandler() {
		Article article1 = new Article("1", "Uno", "Uno Desc");
		BDDMockito.given(this.articleRepository.save(BDDMockito.any())).willReturn(Mono.just(article1));

		webTestClient.mutate().filter(basicAuthentication("user", "password")).build()
			.post()
			.uri("/api/articles")
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK);

	}
}
