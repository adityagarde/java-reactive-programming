package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class _11StockPriceExample {

    /**
     * A simple example of Flux which continuously emits the price of a particular stock.
     * Subscription is cancelled if the price of the stock rises above 110 or falls below 90.
     */
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        getPrice().subscribeWith(new Subscriber<Integer>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer price) {
                System.out.println(LocalDateTime.now() + " Price == " + price);
                if (price > 110 || price < 90) {
                    this.subscription.cancel();
                    latch.countDown();
                }
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("Complete!");
                latch.countDown();
            }
        });

        latch.await();
    }

    public static Flux<Integer> getPrice() {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> atomicInteger.getAndAccumulate(
                        Util.faker().random().nextInt(-5, 5),
                        (a, b) -> a + b
                ));
    }
}
