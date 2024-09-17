package zzzank.libs.config.impl.bound.complex.array;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.impl.bound.DefaultBound;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author ZZZank
 */
public class ArrayBound<T> extends DefaultBound<T[]> {
    private final ConfigBound<T> memberBound;
    private final T[] typeRef;

    public ArrayBound(@NotNull T[] defaultValue, @NotNull ConfigBound<T> memberBound) {
        super(defaultValue);
        this.memberBound = Objects.requireNonNull(memberBound);
        this.typeRef = (T[]) Array.newInstance(defaultValue.getClass().componentType(), 0);
    }

    @Override
    public boolean test(Object value) {
        return value != null && (value instanceof Iterable<?> || value.getClass().isArray());
    }

    @Override
    public T[] adapt(Object value) {
        if (!test(value)) {
            return defaultValue;
        }
        val list = new ArrayList<T>();
        if (value instanceof Iterable<?> iterable) {
            for (val o : iterable) {
                list.add(memberBound.adapt(o));
            }
        } else if (value.getClass().isArray()) {
            val size = Array.getLength(value);
            for (int i = 0; i < size; i++) {
                list.add(memberBound.adapt(Array.get(value, i)));
            }
        }
        return list.toArray(typeRef);
    }
}
