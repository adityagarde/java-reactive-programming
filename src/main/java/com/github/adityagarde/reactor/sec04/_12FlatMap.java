package com.github.adityagarde.reactor.sec04;

import com.github.adityagarde.reactor.sec04.helper.OrderService;
import com.github.adityagarde.reactor.sec04.helper.UserService;
import com.github.adityagarde.reactor.util.Util;

public class _12FlatMap {

    public static void main(String[] args) {
        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}