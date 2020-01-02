package cz.tut.client.stockclient;

import cz.tut.client.stockclient.model.StockPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientTest {
    WebClient webClient = WebClient.builder().build();
    @Test
    public void shoudRetrieveStockPrices() {
        WebClientStockClient stockClient = new WebClientStockClient(webClient);
        Flux<StockPrice> symbols = stockClient.pricesFor("SYMBOL");
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.take(5).count().block() > 0);
        Flux<StockPrice> pricesFive = symbols.take(5);
        Assertions.assertEquals(5, pricesFive.count().block());
        Assertions.assertEquals("SYMBOL", pricesFive.blockFirst().getSymbol());
    }
}