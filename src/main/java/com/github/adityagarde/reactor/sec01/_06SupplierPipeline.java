package com.github.adityagarde.reactor.sec01;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class _06SupplierPipeline {
    public static void main(String[] args) {
        // As we have shifted the business call (blocking) inside the Supplier,
        // The following statements get executed immediately without any delay.
        getName();
        getName();
        getName();

        // When we actually subscribe to the Publisher - we see the delay.
        getName().subscribe(item -> System.out.println(item));
        getName();

        // Async
        getName().subscribeOn(Schedulers.boundedElastic())
                .subscribe(item -> System.out.println(item));
        getName();

        Util.sleepSeconds(4); // Added just to see the output

        // Adding block only for testing purpose, blocking call, Sleep not required.
        String str = getName().subscribeOn(Schedulers.boundedElastic())
                .block();
        System.out.println(str);

        /** OUTPUT of the above code!
         * Inside getName()
         * Inside getName()
         * Inside getName()
         * Inside getName()
         * Generating Name...
         * NIDOKING
         * Inside getName()
         * Inside getName()
         * Inside getName()
         * Generating Name...
         * CHARMANDER
         * Inside getName()
         * Generating Name...
         * ELECTRODE
         */
    }

    // Demo Publisher Stub
    private static Mono<String> getName() {
        System.out.println("Inside getName()");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating Name...");
            Util.sleepSeconds(3);
            return Util.faker().pokemon().name();
        }).map(name -> name.toUpperCase());
    }
}
