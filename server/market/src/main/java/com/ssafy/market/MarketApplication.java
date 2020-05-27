package com.ssafy.market;

import com.ssafy.market.global.config.AppProperties;
//import com.ssafy.market.global.exception.GraphQLErrorAdapter;
//import com.ssafy.market.global.exception.GraphQLErrorHandler;
import com.ssafy.market.global.exception.GraphQLErrorAdapter;
import com.ssafy.market.global.exception.GraphQLErrorHandler;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
public class MarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }
}
