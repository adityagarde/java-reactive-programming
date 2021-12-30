package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class _07Timeout {

    public static void main(String[] args) {

        getOrderNumbersInFive()
                .timeout(Duration.ofSeconds(2), fallBack())
                .subscribe(Util.subscriber());
        // Gives error - Error : Did not observe any item or terminal signal within 2000ms in 'concatMap'
        // (and no fallback has been configured) - when fallback method is not passed.

        getOrderNumbersInOne()
                .timeout(Duration.ofSeconds(2), fallBack())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<Integer> getOrderNumbersInFive() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5));
    }

    private static Flux<Integer> getOrderNumbersInOne() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> fallBack() {
        return Flux.range(10, 10)
                .delayElements(Duration.ofMillis(200));
    }
}
