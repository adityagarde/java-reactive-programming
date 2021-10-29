package com.github.adityagarde.reactor.sec01;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class _05MonoFromSupplier {

    public static void main(String[] args) {

        /**
         * AVOID - Mono<String> mono = Mono.just(getName());
         * In this case there were no Subscribers but still the getName is getting executed as we can see in the log.
         * => Use just only when you have data already!
         */

        Mono<String> mono = Mono.fromSupplier(() -> getName());

        mono.subscribe(item -> System.out.println(item));

        // This can be done from both Supplier and Callable interfaces
        Supplier<String> stringSupplier = () -> getName();
        Mono<String> mono1 = Mono.fromSupplier(stringSupplier);
        mono1.subscribe(item -> System.out.println(item));

        Callable<String> stringCallable = () -> getName();
        Mono<String> mono2 = Mono.fromCallable(stringCallable);
        mono2.subscribe(item -> System.out.println(item));
    }

    private static String getName() {
        System.out.println("Generating Name...");
        return Util.faker().funnyName().name();
    }
}
