package com.common.eventbus.api;

import com.common.eventbus.model.Event;

public interface EventHandler {

    String consumerName();

    boolean supports(String topic, String type);

    void handle(Event event) throws Exception;
}
