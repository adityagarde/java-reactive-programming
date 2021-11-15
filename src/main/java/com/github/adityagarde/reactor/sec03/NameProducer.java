package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void produce() {
        String name = Util.faker().pokemon().name();
        this.fluxSink.next(Thread.currentThread().getName() + " - " + name);
    }
}
