package com.common.eventbus.engine;

import com.common.eventbus.model.EventSubscription;
import com.common.eventbus.repository.EventSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventScheduler {

    private final EventSubscriptionRepository subRepo;
    private final EventDispatcher dispatcher;

    @Scheduled(fixedDelay = 5000)
    public void poll() throws Exception {
        List<EventSubscription> subs =
                subRepo.fetchPending("THIS_SERVICE", 50);

        for (EventSubscription sub : subs) {
            dispatcher.dispatch(sub);
        }
    }
}

