package zzzank.libs.config.impl.bound;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.Objects;

/**
 * @author ZZZank
 */
public class DefaultOnlyBound<T> implements ConfigBound<T> {

    private final T value;

    public DefaultOnlyBound(T defaultValue) {
        this.value = Objects.requireNonNull(defaultValue);
    }

    @Override
    public @NotNull T getDefault() {
        return value;
    }

    @Override
    public boolean test(T value) {
        return value != null;
    }

    @Override
    public @NotNull T adapt(Object value) {
        return value.getClass().isInstance(value) ? (T) value : this.value;
    }
}
