package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

public class _03DoCallbacks {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            System.out.println("Inside create!");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
            System.out.println("Completed!!!");
        })
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() -> System.out.println("doFirst 1"))
                .doOnNext(o -> System.out.println("doOnNext - " + o))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe - 1 - " + subscription))
                .doOnRequest(value -> System.out.println("doOnRequest - " + value))
                .doOnError(throwable -> System.out.println("doOnError - " + throwable.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doFirst(() -> System.out.println("doFirst 2"))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe - 2 - " + subscription))
                .doFinally(signalType -> System.out.println("doFinally - " + signalType))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard - " + o))
                .doFirst(() -> System.out.println("doFirst 3"))
                .subscribe(Util.subscriber());


    /*
        doFirst 3
        doFirst 2
        doFirst 1
        doOnSubscribe - 1 - reactor.core.publisher.FluxPeekFuseable$PeekConditionalSubscriber@5ae9a829
        doOnSubscribe - 2 - reactor.core.publisher.FluxPeekFuseable$PeekConditionalSubscriber@6d8a00e3
        doOnRequest - 9223372036854775807
        Inside create!
        doOnNext - 0
        Received : 0
        doOnNext - 1
        Received : 1
        doOnNext - 2
        Received : 2
        doOnNext - 3
        Received : 3
        doOnNext - 4
        Received : 4
        doOnComplete
        doOnTerminate
        Completed!
        doFinally - onComplete
        Completed!!!
     */

        /*
         * doOnFirst - tasks which are executed first. The request goes from bottom to top and thus multiple
         * doOnFirsts are executed in reverse order as mentioned in the pipeline.
         *
         * doOnSubscribe - tasks which are executed on getting the subscribed object. The response i.e. subscription
         * object comes top to bottom, and thus multiple doOnSubscribes are executed in that order.
         *
         * Only after doOnRequest - the contents of `Flux.create()` are executed.
         *
         * doOnComplete - executed for Success cases.
         * doOnTerminate - executed for both Success and Error cases (doOnError).
         * doOnCancel - when cancelled - doOnTerminate is not called in such cases.
         * doFinally - executed in the end, after doOnTerminate and doOnComplete.
         */

        Flux.create(fluxSink -> {
            System.out.println("Inside create!");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.error(new RuntimeException("Error Occurred!"));
            System.out.println("Completed!!!");
        })
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() -> System.out.println("doFirst"))
                .doOnNext(o -> System.out.println("doOnNext - " + o))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe -" + subscription))
                .doOnRequest(value -> System.out.println("doOnRequest - " + value))
                .doOnError(throwable -> System.out.println("doOnError - " + throwable.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doFinally(signalType -> System.out.println("doFinally - " + signalType))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard - " + o))
                .take(2) //sends cancel request
                .doFinally(signalType -> System.out.println("doFinally - " + signalType))
                .subscribe(Util.subscriber());

        /*
            doFirst
            doOnSubscribe -reactor.core.publisher.FluxPeekFuseable$PeekConditionalSubscriber@59fa1d9b
            doOnRequest - 9223372036854775807
            Inside create!
            doOnNext - 0
            Received : 0
            doOnNext - 1
            Received : 1
            doOnCancel
            doFinally - cancel
            Completed!
            doFinally - onComplete
            doOnDiscard - 2
            doOnDiscard - 3
            doOnDiscard - 4
            Completed!!!
         */
    }
}