package zzzank.libs.config.impl.bound.complex.array;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.impl.bound.UnifiedBound;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * @author ZZZank
 */
public class BoolArrayBound extends UnifiedBound<boolean[]> {
    public BoolArrayBound(boolean @NotNull [] defaultValue) {
        super(defaultValue);
    }

    @Override
    public boolean @NotNull [] provideDefault() {
        return Arrays.copyOf(defaultValue, defaultValue.length);
    }

    @Override
    public Optional<boolean[]> tryAdapt(Object o) {
        if (o == null) {
            return Optional.empty();
        } else if (o instanceof Collection<?> collection) {
            val parsed = new boolean[collection.size()];
            var size = 0;
            for (val object : collection) {
                parsed[size++] = Boolean.parseBoolean(String.valueOf(object));
            }
            return Optional.of(parsed);
        } else if (o.getClass().isArray()) {
            if (o.getClass().componentType() == boolean.class) {
                return Optional.of((boolean[]) o);
            }
            val size = Array.getLength(o);
            val parsed = new boolean[size];
            for (int i = 0; i < size; i++) {
                parsed[i] = Boolean.parseBoolean(String.valueOf(Array.get(o, i)));
            }
            return Optional.of(parsed);
        }
        return Optional.empty();
    }
}
