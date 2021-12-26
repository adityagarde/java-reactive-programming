package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _01Handle {

    public static void main(String[] args) {

        // handle = filter + map
        Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    if (integer % 2 == 0)
                        synchronousSink.next(integer); // ~filter
                    else
                        synchronousSink.next(integer + "a"); //~map
                }).subscribe(Util.subscriber());

        Flux.range(1, 20)
                .handle((integer, synchronousSink) -> {
                    if (integer == 8)
                        synchronousSink.complete();
                    else
                        synchronousSink.next(integer * 2);
                }).subscribe(Util.subscriber());

    }
}
