package com.github.adityagarde.reactor.sec02;

import reactor.core.publisher.Flux;

public class _02MultipleSubscribers {

    public static void main(String[] args) {

        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);
        Flux<Integer> evenFlux = integerFlux.filter(i -> i % 2 == 0);

        integerFlux.subscribe(item -> System.out.println("Subscriber 1 : " + item));

        integerFlux.subscribe(item -> System.out.println("Subscriber 2 : " + item));

        evenFlux.subscribe(item -> System.out.println("Subscriber 3 : " + item));
    }
}
