package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _04FluxCreateIssueFix {

    public static void main(String[] args) {

        /*
         * Here if we don't have `!fluxSink.isCancelled()` in our logic then the Publisher
         * keeps on Emitting data even after the Subscription is cancelled because of `take()` operator.
         */

        Flux.create(fluxSink -> {
            String country = "";
            do {
                country = Util.faker().country().name();
                System.out.println("Emitted : " + country);
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("India") && !fluxSink.isCancelled());
            fluxSink.complete();
        })
                .take(3)
                .subscribe(Util.subscriber());
    }
}
