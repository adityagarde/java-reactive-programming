package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _09SwitchIfEmpty {

    // Fallback method if the original condition is not met
    // Ex use case - If we don't find the data in our cache, then fallback method will directly query the DB.
    public static void main(String[] args) {
        getNumbers()
                .filter(i -> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 10);
    }

    private static Flux<Integer> fallback() {
        return Flux.range(20, 10);
    }
}
