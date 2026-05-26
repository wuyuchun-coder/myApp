package com.myapp.domain.shared;

import java.io.Serializable;
import java.util.Objects;

/**
 * 领域标识基类（值对象）。
 */
public abstract class Identifier<T extends Serializable> implements ValueObject {

    private final T value;

    protected Identifier(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Identifier value must not be null");
        }
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identifier<?> that = (Identifier<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
