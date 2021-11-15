package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _03FluxTakeOperator {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .log()
                .take(3)
                .log()
                .subscribe(Util.subscriber());

        /*
         * the `take()` operator will accept 3 values via 3 `onNext()` calls and when 3 items are received -
         * it will trigger a `cancel()` and then an `onComplete()`.
         */

        /* o/p -
            [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxRange.RangeSubscription)
            [ INFO] (main) | onSubscribe([Fuseable] FluxTake.TakeFuseableSubscriber)
            [ INFO] (main) | request(unbounded)
            [ INFO] (main) | request(unbounded)
            [ INFO] (main) | onNext(1)
            [ INFO] (main) | onNext(1)
            Received : 1
            [ INFO] (main) | onNext(2)
            [ INFO] (main) | onNext(2)
            Received : 2
            [ INFO] (main) | onNext(3)
            [ INFO] (main) | onNext(3)
            Received : 3
            [ INFO] (main) | cancel()
            [ INFO] (main) | onComplete()
            Completed!
         */

    }
}
