package com.github.adityagarde.reactor.sec01;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Mono;

public class _08MonoFromRunnable {
    public static void main(String[] args) {

        Mono.fromRunnable(timeConsumingTask())
                .subscribe(item -> System.out.println(item),
                           ex -> System.out.println("Error : " + ex.getMessage()),
                           () -> System.out.println("Task A Completed, Initiate Task B"));

    }

    private static Runnable timeConsumingTask() {
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Task Completed!");
        };
    }
}
