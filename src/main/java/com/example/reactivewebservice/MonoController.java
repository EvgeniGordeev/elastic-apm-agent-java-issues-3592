package com.example.reactivewebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple6;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MonoController {
    private final WebClient.Builder webClientBuilder;

    public MonoController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/coins")
    public Mono<Map> coins() {
        WebClient webClient = webClientBuilder.baseUrl("https://api.coindesk.com").build();
        return webClient.get().uri("/v1/bpi/currentprice.json")
            .retrieve().bodyToMono(Map.class);
    }

    @GetMapping("/cats")
    public Mono<Tuple6> cats(
    ) {
        WebClient webClient = webClientBuilder.baseUrl("https://catfact.ninja/fact").build();
        // every time there is a new fact
        Mono<CatFacts> cat1 = webClient.get().retrieve().bodyToMono(CatFacts.class);
        Mono<CatFacts> cat2 = webClient.get().retrieve().bodyToMono(CatFacts.class);
        Mono<CatFacts> cat3 = webClient.get().retrieve().bodyToMono(CatFacts.class);
        Mono<CatFacts> cat4 = webClient.get().retrieve().bodyToMono(CatFacts.class);
        Mono<CatFacts> cat5 = webClient.get().retrieve().bodyToMono(CatFacts.class);
        Mono<CatFacts> cat6 = webClient.get().retrieve().bodyToMono(CatFacts.class);
        return Mono.zip(cat1, cat2, cat3, cat4, cat5, cat6).map(cats -> cats);
    }

    static class CatFacts {
        String fact;
        int length;

        public String getFact() {
            return fact;
        }

        public void setFact(String fact) {
            this.fact = fact;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
