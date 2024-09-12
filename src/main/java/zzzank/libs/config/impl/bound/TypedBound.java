package zzzank.libs.config.impl.bound;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author ZZZank
 */
public class TypedBound<T> extends DefaultBound<T> {
    protected final Class<T> type;

    public TypedBound(T defaultValue, @NotNull Class<T> type) {
        super(defaultValue);
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public boolean test(Object value) {
        return type.isInstance(value);
    }

    @Override
    public T adapt(Object value) {
        return test(value) ? (T) value : this.defaultValue;
    }
}
