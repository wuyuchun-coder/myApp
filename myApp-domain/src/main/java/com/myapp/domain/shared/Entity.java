package com.myapp.domain.shared;

import java.io.Serializable;
import java.util.Objects;

/**
 * 实体基类，通过标识判断相等性。
 */
public abstract class Entity<ID extends Identifier<?>> implements Serializable {

    protected ID id;

    protected Entity() {
    }

    protected Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
