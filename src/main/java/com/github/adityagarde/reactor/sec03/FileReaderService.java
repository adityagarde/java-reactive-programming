package com.github.adityagarde.reactor.sec03;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileReaderService {

    private Callable<BufferedReader> openReader(Path path) {
        return () -> Files.newBufferedReader(path);
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
        return (bufferedReader, synchronousSink) -> {
            try {
                String line = bufferedReader.readLine();
                System.out.println("Reading : " + line); // Added to show that it reads 1 line and emits it, doesn't bulk read it.
                if (Objects.isNull(line)) {
                    synchronousSink.complete();
                } else {
                    synchronousSink.next(line);
                }
            } catch (IOException e) {
                synchronousSink.error(e);
            }
            return bufferedReader;
        };
    }

    private Consumer<BufferedReader> closeReader() {
        return bufferedReader -> {
            try {
                bufferedReader.close();
                System.out.println("File Closed!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public Flux<String> read(Path path) {
        return Flux.generate(
                openReader(path),
                read(),
                closeReader()
        );
    }

}