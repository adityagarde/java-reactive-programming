package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _10MonoFromFlux {
    public static void main(String[] args) {

        Flux.range(1, 5)
                .map(item -> Util.faker().pokemon().name())
                .next()
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );
        /* o/p -
         * Received : Moltres
         * Completed
         * Returns only 1 item even when asked for 5 - behaves like a Mono!
         */

        Flux.range(1, 10)
                .filter(item -> item > 4)
                .next()
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );
        /* o/p -
         * Received : 5
         * Completed
         * What the Flux behaving as Mono returns can be decided using stream pipeline before next().
         */

        Flux.range(1, 10)
                .next()
                .filter(item -> item > 4)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );
        /*
         * As "1" doesn't match filter condition added after the `next()` call - nothing is returned and `onComplete()`
         * is executed. `next()` converts Flux to Mono.
         */
    }
}
