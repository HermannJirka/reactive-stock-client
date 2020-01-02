package cz.tut.client.stockclient.configuration;

import cz.tut.client.stockclient.WebClientStockClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public WebClientStockClient webClientStockClient(){
        return new WebClientStockClient(webClient());
    }
    @Bean
    @ConditionalOnMissingBean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
