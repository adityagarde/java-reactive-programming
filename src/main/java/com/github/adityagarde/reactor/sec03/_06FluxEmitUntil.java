package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _06FluxEmitUntil {

    public static void main(String[] args) {

        // Emit countries, stop only after when you see India
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("India")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());

    }
}
