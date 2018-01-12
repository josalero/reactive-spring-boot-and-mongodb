package com.josalero.reactive.api.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.josalero.reactive.entity.Article;
import com.josalero.reactive.repository.ArticleRepository;

import reactor.core.publisher.Mono;

@Component
public class ApiHandler {

	@Autowired
	private ArticleRepository articleRepository;
	
	
	public ApiHandler(final ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

    public Mono<ServerResponse> findAll(ServerRequest req) {
        return ServerResponse.ok().body(articleRepository.findAll(), Article.class);
    }
    
	public Mono<ServerResponse> postArticle(final ServerRequest request) {
		return request.bodyToMono(Article.class).log()
				 .flatMap( article -> articleRepository.save(article))
				 .flatMap(article -> ServerResponse.created(URI.create("/api/articles" + article.getId()))
				 .build());
	}


}
