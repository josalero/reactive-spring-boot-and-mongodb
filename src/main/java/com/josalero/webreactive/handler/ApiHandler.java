package com.josalero.webreactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.josalero.webreactive.entity.Article;
import com.josalero.webreactive.repository.ArticleRepository;

import reactor.core.publisher.Mono;

@Component
public class ApiHandler {

	@Autowired
	private ArticleRepository articleRepository;
	
	
	public ApiHandler(final ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public Mono<ServerResponse> postArticle(final ServerRequest request) {
		return articleRepository.save(request.bodyToMono(Article.class).block()).transform(this::serverResponse);
	}

    Mono<ServerResponse> serverResponse(Mono<Article> articleMono) {
        return articleMono.flatMap(articleResponse ->
                ServerResponse.ok().body(Mono.just(articleResponse), Article.class));
    }
}
