package com.myapp.domain.shared;

import java.time.Instant;
import java.util.UUID;

/**
 * 领域事件基类。
 */
public abstract class DomainEvent implements ValueObject {

    private final String eventId;
    private final Instant occurredOn;

    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Instant.now();
    }

    public String getEventId() {
        return eventId;
    }

    public Instant getOccurredOn() {
        return occurredOn;
    }
}
