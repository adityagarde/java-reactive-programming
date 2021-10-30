package com.github.adityagarde.reactor.sec01;

import com.github.adityagarde.reactor.util.Util;

public class _09Main {

    public static void main(String[] args) {
        FileService.read("file01.txt")
                .subscribe(item -> System.out.println(item),
                        ex -> System.out.println(ex.getMessage()),
                        () -> System.out.println("Completed!"));

        FileService.write("file03.txt", "This is File 03!")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        FileService.delete("file04.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
