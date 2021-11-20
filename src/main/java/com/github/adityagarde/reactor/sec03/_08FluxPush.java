package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _08FluxPush {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        Flux.push(nameProducer)
                .subscribe(Util.subscriber());

        nameProducer.produce();

        Runnable runnable = () -> nameProducer.produce();

        for (int i = 0; i < 15; i++) {
            new Thread(runnable).start();
        }

        /*
         * `Flux.create()` is thread safe, we can share it with multiple threads
         * `Flux.push()`is NOT thread safe - we can use it for single thread producers.
         * Some items can be missed in the above implementation.
         */

        Util.sleepSeconds(2);
    }
}
