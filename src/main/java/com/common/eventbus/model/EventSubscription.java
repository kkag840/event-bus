package com.common.eventbus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventSubscription {
    private UUID id;
    private UUID eventId;
    private String consumer;
    private String status; // PENDING, PROCESSING, DONE, FAILED
    private int retryCount;
    private long updated;
}
