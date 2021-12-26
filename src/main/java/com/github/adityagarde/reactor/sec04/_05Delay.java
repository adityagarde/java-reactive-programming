package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;
import reactor.util.concurrent.Queues;

import java.time.Duration;

public class _05Delay {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.x", "9");

        Flux.range(1, 100) // request(32) since by default reactor.bufferSize.x == 32
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }
}
