package com.myapp.domain.shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根基类，负责收集并暴露领域事件。
 */
public abstract class AggregateRoot<ID extends Identifier<?>> extends Entity<ID> {

    private final transient List<DomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot() {
        super();
    }

    protected AggregateRoot(ID id) {
        super(id);
    }

    protected void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return Collections.unmodifiableList(events);
    }
}
