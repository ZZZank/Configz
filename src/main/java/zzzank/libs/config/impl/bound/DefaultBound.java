package zzzank.libs.config.impl.bound;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;

/**
 * @author ZZZank
 */
public class DefaultBound<T> implements ConfigBound<T> {

    protected final T defaultValue;

    public DefaultBound(@NotNull T defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public @NotNull T getDefault() {
        return defaultValue;
    }

    @Override
    public boolean test(Object value) {
        return this.defaultValue.getClass().isInstance(value);
    }

    @Override
    public T adapt(Object value) {
        return value.getClass().isInstance(value) ? (T) value : this.defaultValue;
    }
}
