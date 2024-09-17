package zzzank.libs.config.impl.bound.complex.array;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.UnifiedTestAdaptBound;
import zzzank.libs.config.impl.bound.DefaultBound;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * @author ZZZank
 */
public class LongArrayBound extends DefaultBound<long[]> implements UnifiedTestAdaptBound<long[]> {
    public LongArrayBound(long @NotNull [] defaultValue) {
        super(defaultValue);
    }

    @Override
    public Optional<long[]> tryAdapt(Object o) {
        if (o == null) {
            return Optional.empty();
        } else if (o instanceof Collection<?> collection) {
            val parsed = new long[collection.size()];
            var size = 0;
            for (val object : collection) {
                try {
                    parsed[size++] = Long.parseLong(String.valueOf(object));
                } catch (NumberFormatException ignored) {}
            }
            return Optional.of(size == collection.size()
                ? parsed
                : Arrays.copyOf(parsed, size)
            );
        } else if (o.getClass().isArray()) {
            if (o.getClass().componentType() == long.class) {
                return Optional.of((long[]) o);
            }
            val sizeOriginal = Array.getLength(o);
            var size = 0;
            val parsed = new long[sizeOriginal];
            for (int i = 0; i < sizeOriginal; i++) {
                try {
                    parsed[size++] = Long.parseLong(String.valueOf(Array.get(o, i)));
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
