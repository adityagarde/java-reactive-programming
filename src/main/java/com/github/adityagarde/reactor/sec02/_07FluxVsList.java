package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;

import java.util.List;

public class _07FluxVsList {
    public static void main(String[] args) {

        List<String> names = NameGenerator.getNamesUsingList(5);
        System.out.println(names);

        NameGenerator.getNamesUsingFlux(5)
                .subscribe(Util.onNext());

        /*
         * Next Time taken is more or less equal for both - but the List approach returns data only after all 5 are ready.
         * While the Flux returns the data as soon as it is ready.
         */
    }
}
