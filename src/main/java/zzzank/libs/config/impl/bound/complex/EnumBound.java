package zzzank.libs.config.impl.bound.complex;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zzzank.libs.config.impl.bound.TypedBound;

import java.util.Objects;

/**
 * @author ZZZank
 */
public class EnumBound<T extends Enum<T>> extends TypedBound<T> {
    public EnumBound(@NotNull T defaultValue) {
        super(Objects.requireNonNull(defaultValue), (Class<T>) defaultValue.getClass());
    }

    public EnumBound(@Nullable T defaultValue, @NotNull Class<T> type) {
        super(defaultValue, type);
    }

    @Override
    public boolean test(Object value) {
        return super.test(value) || value instanceof CharSequence;
    }

    @Override
    public T adapt(Object value) {
        if (super.test(value)) {
            return (T) value;
        } else if (value instanceof CharSequence) {
            return Enum.valueOf(type, value.toString());
        }
        return defaultValue;
    }
}
