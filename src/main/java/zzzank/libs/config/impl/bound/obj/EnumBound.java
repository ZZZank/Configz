package zzzank.libs.config.impl.bound.obj;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.impl.bound.DefaultImmutableBound;

import java.util.Objects;

/**
 * @author ZZZank
 */
public class EnumBound<T extends Enum<T>> extends DefaultImmutableBound<T> {
    public EnumBound(@NotNull T defaultValue) {
        super(Objects.requireNonNull(defaultValue));
    }

    @Override
    public boolean test(Object value) {
        return super.test(value) || value instanceof CharSequence;
    }

    @Override
    public T adapt(Object value) {
        if (!super.test(value)) {
            return defaultValue;
        }
        val type = (Class<T>) defaultValue.getClass();
        if (type.isInstance(value)) {
            return (T) value;
        } else if (value instanceof CharSequence) {
            return Enum.valueOf((Class<T>) defaultValue.getClass(), value.toString());
        }
        return defaultValue;
    }
}
