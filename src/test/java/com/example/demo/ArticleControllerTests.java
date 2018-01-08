package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.josalero.webreactive.entity.Article;
import com.josalero.webreactive.repository.ArticleRepository;

import reactor.core.publisher.Flux;


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
	public void listArticles() {

		BDDMockito.given(this.articleRepository.findAll()).willReturn(articleFlux);

		webTestClient.get()
			.uri("/articles")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectBodyList(Article.class).hasSize(3);

	}
}
