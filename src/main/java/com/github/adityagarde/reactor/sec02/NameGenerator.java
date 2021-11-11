package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    public static List<String> getNamesUsingList(int count) {
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(getName());
        }
        return list;
    }

    public static Flux<String> getNamesUsingFlux(int count) {
        return Flux.range(0, count)
                .map(item -> getName());
    }

    private static String getName() {
        Util.sleepSeconds(1);
        return Util.faker().pokemon().name();
    }
}
