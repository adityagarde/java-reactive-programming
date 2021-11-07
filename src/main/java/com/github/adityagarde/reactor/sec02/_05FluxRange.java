package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _05FluxRange {

    public static void main(String[] args) {

        Flux.range(3, 5)
                .subscribe(item -> System.out.println("Received : " + item));

        /*
            Received : 3
            Received : 4
            Received : 5
            Received : 6
            Received : 7
         */

        Flux.range(3, 3)
                .log()
                .map(item -> Util.faker().pokemon().name())
                .log()
                .subscribe(Util.onNext());

        /*
            [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxRange.RangeSubscription)
            [ INFO] (main) | onSubscribe([Fuseable] FluxMapFuseable.MapFuseableSubscriber)
            [ INFO] (main) | request(unbounded)
            [ INFO] (main) | request(unbounded)
            [ INFO] (main) | onNext(3)
            [ INFO] (main) | onNext(Kingler)
            Received : Kingler
            [ INFO] (main) | onNext(4)
            [ INFO] (main) | onNext(Magnemite)
            Received : Magnemite
            [ INFO] (main) | onNext(5)
            [ INFO] (main) | onNext(Gastly)
            Received : Gastly
            [ INFO] (main) | onComplete()
            [ INFO] (main) | onComplete()
         */
    }
}