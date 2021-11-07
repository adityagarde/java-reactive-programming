package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _01FluxIntro {

    public static void main(String[] args) {

        // Simplest way to create flux when we already have the data.

        Flux<Integer> flux1 = Flux.just(1);
        flux1.subscribe(Util.onNext());

        Flux<Integer> flux2 = Flux.just(1, 2, 3, 4, 5);
        flux2.subscribe(
                item -> System.out.println("Received : " + item),
                ex -> System.out.println("Error : " + ex.getMessage()),
                () -> System.out.println("Completed!"));

        Flux<Integer> flux0 = Flux.empty();
        flux0.subscribe(
                item -> System.out.println("Received : " + item),
                ex -> System.out.println("Error : " + ex.getMessage()),
                () -> System.out.println("Completed!"));


        Flux<Object> flux3 = Flux.just("1", 1, "One", Util.faker().ancient().titan(), 23.90, false);
        flux3.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());

    }
}
