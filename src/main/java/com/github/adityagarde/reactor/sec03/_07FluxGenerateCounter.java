package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _07FluxGenerateCounter {

    public static void main(String[] args) {

        /*
         * New requirement is to Stop emitting after receiving India or after receiving 10 items, which ever is the earliest.
         */

        /*
         * The problem with `generate(Consumer<SynchronousSink<T>> generator)` implementation is that we cannot have
         * a counter variable inside the block as with `generate()` the complete code block is invoked
         * and thus the counter will also be reset to zero on every call.
         *
         * We have a separate implementation `generate(Callable<S> stateSupplier, BiFunction<S, SynchronousSink<T>, S> generator)`
         * which accepts a Callable stateSupplier i.e. the initial state and a BiFunction taking the state (counter) and
         * SynchronousSink and returning the new state (counter) back.
         */

        Flux.generate(
                () -> 1,
                (counter, synchronousSink) -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if (counter >= 10 || country.equalsIgnoreCase("India")) {
                        synchronousSink.complete();
                    }
                    return counter + 1;
                }
        ).subscribe(Util.subscriber());

    }
}