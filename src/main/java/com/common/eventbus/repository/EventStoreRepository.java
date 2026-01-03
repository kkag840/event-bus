package com.common.eventbus.repository;

import com.common.eventbus.model.Event;

import java.util.Optional;
import java.util.UUID;

public interface EventStoreRepository {
    Event save(Event event);
    Optional<Event> findById(UUID id);
}
