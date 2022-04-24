package com.github.adityagarde.reactor.sec04.helper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

    static {
        List<PurchaseOrder> orderList1 = Arrays.asList(
                new PurchaseOrder(1),
                new PurchaseOrder(1),
                new PurchaseOrder(1)
        );
        db.put(1, orderList1);

        List<PurchaseOrder> orderList2 = Arrays.asList(
                new PurchaseOrder(2),
                new PurchaseOrder(2),
                new PurchaseOrder(2),
                new PurchaseOrder(2)
        );
        db.put(2, orderList2);
    }

    public static Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> {
            db.get(userId).forEach(purchaseOrderFluxSink::next);
            purchaseOrderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1));
    }
}
