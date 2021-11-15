package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _01FluxCreate {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            fluxSink.complete();
        }).subscribe(Util.subscriber());

        Flux.create(fluxSink -> {
            for (int i = 0; i < 3; i++) {
                fluxSink.next(Util.faker().pokemon().name());
            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());

        Flux.create(fluxSink -> {
            String country = "";
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("India"));
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}