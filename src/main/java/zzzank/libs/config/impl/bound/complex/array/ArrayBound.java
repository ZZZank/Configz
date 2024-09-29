package zzzank.libs.config.impl.bound.complex.array;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.impl.bound.UnifiedBound;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ZZZank
 */
public class ArrayBound<T> extends UnifiedBound<T[]> {
    private final ConfigBound<T> memberBound;
    private final T[] typeRef;
    private final boolean skipDefault;

    public ArrayBound(@NotNull T[] defaultValue, @NotNull ConfigBound<T> memberBound) {
        this(defaultValue, memberBound, false);
    }

    public ArrayBound(@NotNull T[] defaultValue, @NotNull ConfigBound<T> memberBound, boolean skipDefault) {
        super(defaultValue);
        this.memberBound = Objects.requireNonNull(memberBound);
        this.typeRef = (T[]) Array.newInstance(defaultValue.getClass().componentType(), 0);
        this.skipDefault = skipDefault;
    }

    @Override
    public T @NotNull [] provideDefault() {
        return Arrays.copyOf(defaultValue, defaultValue.length);
    }

    @Override
    public Optional<T[]> tryAdapt(Object value) {
        if (value == null) {
            return Optional.empty();
        }
        val list = new ArrayList<T>();
        if (value instanceof Iterable<?> iterable) {
            for (val o : iterable) {
                if (skipDefault && !memberBound.test(value)) {
                    continue;
                }
                list.add(memberBound.adapt(o));
            }
            return Optional.of(list.toArray(typeRef));
        } else if (value.getClass().isArray()) {
            val size = Array.getLength(value);
            for (int i = 0; i < size; i++) {
                if (skipDefault && !memberBound.test(value)) {
                    continue;
                }
                list.add(memberBound.adapt(Array.get(value, i)));
            }
            return Optional.of(list.toArray(typeRef));
        }
        return Optional.empty();
    }
}
