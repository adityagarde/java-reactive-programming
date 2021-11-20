package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _05FluxGenerate {

    public static void main(String[] args) {

        /*
         * Using FluxSink we can keep on emitting data, we can use all loops as well.
         * But with one instance of SynchronousSink we can emit only one item.
         */

        Flux.generate(synchronousSink -> {
            synchronousSink.next("SyncSink 1 - " + Util.faker().country().name());
            synchronousSink.next("SyncSink 2 - " + Util.faker().country().name());
        }).subscribe(Util.subscriber());

        /* o/p-
         * Received : SyncSink 1 - Germany
         * Error : More than one call to onNext
         */

        Flux.generate(synchronousSink -> {
            synchronousSink.next(Util.faker().country().name());
            // synchronousSink.complete(); // uncomment to stop the infinite loop.
        }).subscribe(Util.subscriber());

        // This goes in infinite loop!

        /*
         * With `Flux.create()` i.e. FluxSink we have to maintain the loop separately.
         * While the `Flux.generate()` keeps on invoking the code block again and again.
         * Everytime a separate object of SynchronousSink is generated which emits one object.
         */

        Flux.generate(synchronousSink -> {
            System.out.println("Emitting - ");
            synchronousSink.next(Util.faker().country().name());
        })
                .take(3)
                .subscribe(Util.subscriber());

        // This will emit only 3 items as we have mentioned `take(3)`.

        Flux.generate(synchronousSink -> {
            System.out.println("Emitting - ");
            synchronousSink.next(Util.faker().country().name());
            synchronousSink.complete();
        })
                .take(3)
                .subscribe(Util.subscriber());

        // Even when we have mentioned `take(3)` this will emit only 1 item because of `complete`.

        Flux.generate(synchronousSink -> {
            System.out.println("Emitting - ");
            synchronousSink.next(Util.faker().country().name());
            synchronousSink.error(new Throwable("Demo Error!"));
        })
                .take(3)
                .subscribe(Util.subscriber());

        // Even this will end after emitting only one item because of `error`.
    }
}
