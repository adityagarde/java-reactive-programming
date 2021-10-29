package com.github.adityagarde.reactor.sec01;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Mono;

public class _04MonoEmptyOrError {

    public static void main(String[] args) {
        userRepository(1).subscribe(
                item -> System.out.println("Received : " + item),
                ex -> System.out.println("Error : " + ex.getMessage()),
                () -> System.out.println("Completed!")
        );

        userRepository(2).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        userRepository(34).subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }

    // Publisher Stub
    private static Mono<String> userRepository(int userId) {

        if (userId == 1) {
            return Mono.just(Util.faker().name().firstName());
        } else if (userId == 2) {
            return Mono.empty(); // null
        } else {
            return Mono.error(new RuntimeException("Not in allowed range!"));
        }

    }
}
