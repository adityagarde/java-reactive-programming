package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _08DefaultIfEmpty {

    public static void main(String[] args) {
        getNumbers()
                .filter(i -> i > 10)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 10);
    }
}
