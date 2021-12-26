package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _04LimitRate {

    public static void main(String[] args) {

        Flux.range(1, 1000)
                .log()
                .limitRate(100) // [ INFO] (main) | request(75)
                .subscribe(Util.subscriber());

        Flux.range(1, 1000)
                .log()
                .limitRate(100, 85)
                .subscribe(Util.subscriber());

        /*
         * By default, after 75% objects are received, there is a request for next set of items.
         * Both these values can be controlled from parameters highTide and lowTide(default = 75).
         */
    }
}
