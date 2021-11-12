package com.github.adityagarde.reactor.util;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.time.StopWatch;

import java.util.function.Consumer;

public class Util {

    private static final Faker FAKER = Faker.instance();
    public static StopWatch stopWatch = new StopWatch();

    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received : " + o);
    }

    public static Consumer<Throwable> onError() {
        return e -> System.out.println("Error : " + e.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed");
    }

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startTimer() {
        stopWatch.reset();
        stopWatch.start();
    }

    public static void timeTaken() {
        stopWatch.stop();
        System.out.println("Total Time Taken : " + stopWatch.getTime());
    }

}
