package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class _03FluxFromArrayOrList {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("A", "B", "C");

        Flux.fromIterable(stringList)
                .subscribe(Util.onNext());

        Integer[] intArr = {2, 4, 5, 1, 9, 0};
        Flux.fromArray(intArr)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

    }
}
