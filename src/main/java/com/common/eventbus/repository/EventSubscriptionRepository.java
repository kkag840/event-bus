package com.common.eventbus.repository;

import com.common.eventbus.model.EventSubscription;

import java.util.List;
import java.util.UUID;

public interface EventSubscriptionRepository {

    List<EventSubscription> fetchPending(
            String consumer, int limit);

    void markProcessing(UUID id);
    void markDone(UUID id);
    void markFailed(UUID id);

    EventSubscription save(EventSubscription subscription);
}
