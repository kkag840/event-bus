package com.common.eventbus.publisher;

import java.util.List;

public interface EventPublisher {

    void publish(
            String topic,
            String type,
            Object payload,
            List<String> consumers
    );
}
