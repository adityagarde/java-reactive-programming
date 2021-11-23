package com.github.adityagarde.reactor.sec03;

import com.github.adityagarde.reactor.util.Util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class _09FileReaderService {

    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderService();

        Path path = Paths.get("src/main/resources/sec03/file.txt");

        fileReaderService.read(path)
                .take(25)
                .subscribe(Util.subscriber());

        fileReaderService.read(path)
                .map(str -> {
                    Integer integer = Util.faker().random().nextInt(0, 10);
                    if (integer > 8)
                        throw new RuntimeException("Encountered Error!");
                    return str;
                })
                .take(25)
                .subscribe(Util.subscriber());
    }
}
