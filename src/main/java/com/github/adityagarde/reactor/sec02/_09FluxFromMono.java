package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class _09FluxFromMono {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just(Util.faker().pokemon().name());
        /*
         * There is some existing method which accepts flux (like doSomething()) but we have a mono emitting data.
         * Then obviously doSomething(mono); will not work!
         * Using `Flux.from(Publisher)` will convert it to a Flux.
         */

        Flux<String> flux = Flux.from(mono);

        doSomething(flux);
        flux.subscribe(Util.onNext());
    }

    private static void doSomething(Flux<String> flux) {

    }
}
