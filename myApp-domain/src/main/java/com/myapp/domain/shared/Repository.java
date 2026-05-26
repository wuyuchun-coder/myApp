package com.myapp.domain.shared;

import java.util.Optional;

/**
 * 仓储通用接口，由各限界上下文在 domain 层定义具体仓储。
 */
public interface Repository<T extends AggregateRoot<ID>, ID extends Identifier<?>> {

    void save(T aggregate);

    Optional<T> findById(ID id);

    void remove(T aggregate);
}
