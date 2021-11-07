package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class _04FluxFromStream {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4);
        Stream<Integer> stream = list.stream();

        /**
         * Streams once consumed cannot be reused again,
         * they are closed and if we try to access them we will get an Exception.
         * Example -
         *         stream.forEach(System.out::println);
         *         stream.forEach(System.out::println);
         *
         * "java.lang.IllegalStateException: stream has already been operated upon or closed".
         *
         * Same happens when using `Flux.fromStream()`.
         */

        Flux<Integer> integerFlux = Flux.fromStream(stream);
        /**
         * Flux<Integer> integerFlux = Flux.fromStream(() -> stream));
         * Same will happen with this as well as we are passing the same object to the Supplier().
         */

        integerFlux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());

        integerFlux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());

        /*
            Received : 1
            Received : 2
            Received : 3
            Received : 4
            Completed
            Error : stream has already been operated upon or closed
         */

        Flux<Integer> integerFlux1 = Flux.fromStream(() -> list.stream());

        integerFlux1.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());

        integerFlux1.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());

        /*
            Received : 1
            Received : 2
            Received : 3
            Received : 4
            Completed
            Received : 1
            Received : 2
            Received : 3
            Received : 4
            Completed
         */

    }
}
