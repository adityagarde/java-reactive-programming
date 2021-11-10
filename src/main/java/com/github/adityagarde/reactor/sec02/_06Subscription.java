package com.github.adityagarde.reactor.sec02;

import com.github.adityagarde.reactor.util.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class _06Subscription {

    public static void main(String[] args) {

        AtomicReference<Subscription> atomicReference = new AtomicReference<>();

        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Sub : " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext() : " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError() : " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete()");
                    }
                });

        Util.sleepSeconds(3);
        atomicReference.get().request(3);
        Util.sleepSeconds(5);
        atomicReference.get().request(5);
        Util.sleepSeconds(5);
        System.out.println("Cancelling the subscription!");
        atomicReference.get().cancel();
        Util.sleepSeconds(3);
        atomicReference.get().request(4); // This request is not served since subscription is cancelled!
        Util.sleepSeconds(3);

    }
}
