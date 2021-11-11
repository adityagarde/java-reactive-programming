package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class _08FluxInterval {
    public static void main(String[] args) {
        // Can be used where we need to perform some task periodically
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Util.onNext());

        Util.sleepSeconds(3);
    }
}