package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class _06OnError {

    public static void main(String[] args) {

        // onErrorReturn - Returning a default value on occurrence of error and stopping
        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                .onErrorReturn(-1)
                .subscribe(Util.subscriber());

        // onErrorResume - Calling a different / fall back function on occurrence of error and stopping
        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                .onErrorResume(error -> fallBack())
                .subscribe(Util.subscriber());

        // onErrorContinue - Continues to process ahead when encountered error
        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
                .onErrorContinue((error, object) -> {
                    System.out.println("Warning - Encountered Error");
                })
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallBack() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
