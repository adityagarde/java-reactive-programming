package com.github.adityagarde.reactor.sec01;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class _07MonoFromFuture {

    public static void main(String[] args) {

        Mono.fromFuture(getName())
                .subscribe(item -> System.out.println(item));
        Util.sleepSeconds(1); // Added just to see the output
    }

    public static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Util.faker().programmingLanguage().name());
    }
}
