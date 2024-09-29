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
public class IntArrayBound extends UnifiedBound<int[]> {
    public IntArrayBound(int @NotNull [] defaultValue) {
        super(defaultValue);
    }

    @Override
    public int @NotNull [] provideDefault() {
        return Arrays.copyOf(defaultValue, defaultValue.length);
    }

    @Override
    public Optional<int[]> tryAdapt(Object o) {
        if (o == null) {
            return Optional.empty();
        } else if (o instanceof Collection<?> collection) {
            val parsed = new int[collection.size()];
            var size = 0;
            for (val object : collection) {
                try {
                    parsed[size++] = Integer.parseInt(String.valueOf(object));
                } catch (NumberFormatException ignored) {}
            }
            return Optional.of(size == collection.size()
                ? parsed
                : Arrays.copyOf(parsed, size)
            );
        } else if (o.getClass().isArray()) {
            if (o.getClass().componentType() == int.class) {
                return Optional.of((int[]) o);
            }
            val sizeOriginal = Array.getLength(o);
            var size = 0;
            val parsed = new int[sizeOriginal];
            for (int i = 0; i < sizeOriginal; i++) {
                try {
                    parsed[size++] = Integer.parseInt(String.valueOf(Array.get(o, i)));
                } catch (NumberFormatException ignored) {}
            }
            return Optional.of(size == sizeOriginal
                ? parsed
                : Arrays.copyOf(parsed, size)
            );
        }
        return Optional.empty();
    }
}
