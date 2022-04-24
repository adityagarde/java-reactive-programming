package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.util.Person;
import com.github.adityagarde.reactor.util.Util;
import reactor.core.publisher.Flux;

import java.util.Locale;
import java.util.function.Function;

public class _10Transform {

    public static void main(String[] args) {
        getPersons()
                .transform(applyFilterMap())
                .subscribe(Util.subscriber());
    }

    public static Flux<Person> getPersons() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux.filter(p -> p.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase(Locale.ROOT)))
                .doOnDiscard(Person.class, p -> System.out.println("Discarded Value == " + p));
    }
}