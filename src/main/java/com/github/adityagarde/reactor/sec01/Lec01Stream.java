package com.github.adityagarde.reactor.sec01;

import java.util.stream.Stream;

//Stream Lazy Behaviour
public class Lec01Stream {

    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i * 2;
                });

        // System.out.println(stream); // java.util.stream.ReferencePipeline$3@27716f4
        stream.forEach(System.out::println); // 2
    }
}
