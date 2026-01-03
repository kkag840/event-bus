package com.common.eventbus.engine;

import com.common.eventbus.model.Event;
import com.common.eventbus.model.EventSubscription;
import com.common.eventbus.publisher.EventPublisher;
import com.common.eventbus.repository.EventStoreRepository;
import com.common.eventbus.repository.EventSubscriptionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@RequiredArgsConstructor
public class DefaultEventPublisher implements EventPublisher {

    private final EventStoreRepository eventRepo;
    private final EventSubscriptionRepository subRepo;
    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public void publish(String topic, String type, Object payload, List<String> consumers) {

        Event event = Event.builder()
                .topic(topic)
                .type(type)
                .payload(mapper.writeValueAsString(payload))
                .created(System.currentTimeMillis())
                .build();

        Event saved = eventRepo.save(event);

        for (String consumer : consumers) {
            subRepo.save(EventSubscription.builder()
                    .eventId(saved.getId())
                    .consumer(consumer)
                    .status("PENDING")
                    .retryCount(0)
                    .build());
        }
    }
}

