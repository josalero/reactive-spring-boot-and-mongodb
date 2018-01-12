package com.josalero.reactive.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.josalero.reactive.api.handler.ApiHandler;

@Configuration
@EnableMongoAuditing
public class ArticleRouter {

	public ArticleRouter() {
		// TODO Auto-generated constructor stub
	}
    @Bean
    public RouterFunction<ServerResponse> routes(ApiHandler apiHandler) {
        return route(GET("/api/articles"), apiHandler::findAll)
            .andRoute(POST("/api/articles"), apiHandler::postArticle);
    }
}
