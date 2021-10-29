package com.github.adityagarde.reactor.sec01;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Mono;

public class _03MonoSubscribe {

    public static void main(String[] args) {
        Mono<String> mono1 = Mono.just("ball");

        mono1.subscribe(
                item -> System.out.println(item),
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("Completed Mono1")
        );

        Mono<Integer> mono2 = Mono.just("ball")
                                  .map(str -> str.length())
                                  .map(l -> l / 0);

        mono2.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
