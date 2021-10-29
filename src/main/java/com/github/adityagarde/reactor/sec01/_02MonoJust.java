package com.github.adityagarde.reactor.sec01;

import reactor.core.publisher.Mono;

public class _02MonoJust {
    public static void main(String[] args) {
        // Publisher
        Mono<Integer> mono = Mono.just(1);

        mono.subscribe(i -> System.out.println("Received : " + i));
    }
}
