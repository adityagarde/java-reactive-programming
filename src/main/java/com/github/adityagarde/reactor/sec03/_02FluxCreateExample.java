package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _02FluxCreateExample {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        nameProducer.produce();

        Runnable runnable = () -> nameProducer.produce();

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        // Flux.create(FluxSink) is thread safe!

        Util.sleepSeconds(2);
    }
}
