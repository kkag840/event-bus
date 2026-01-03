package com.common.eventbus.engine;

import com.common.eventbus.api.EventHandler;
import com.common.eventbus.model.Event;
import com.common.eventbus.model.EventSubscription;
import com.common.eventbus.repository.EventStoreRepository;
import com.common.eventbus.repository.EventSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@RequiredArgsConstructor
public class EventDispatcher {

    private final EventStoreRepository eventRepo;
    private final EventSubscriptionRepository subRepo;
    private final List<EventHandler> handlers;

    @Async("eventBusExecutor")
    public void dispatch(EventSubscription sub) throws Exception {

        Event event = eventRepo.findById(sub.getEventId()).orElseThrow();

        for (EventHandler handler : handlers) {
            if (handler.consumerName().equals(sub.getConsumer())
                    && handler.supports(event.getTopic(), event.getType())) {

                handler.handle(event);
                subRepo.markDone(sub.getId());
                return;
            }
        }
    }
}

