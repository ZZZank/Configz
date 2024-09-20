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
public class ShortArrayBound extends UnifiedBound<short[]> {
    public ShortArrayBound(short @NotNull [] defaultValue) {
        super(defaultValue);
    }

    @Override
    public Optional<short[]> tryAdapt(Object o) {
        if (o == null) {
            return Optional.empty();
        } else if (o instanceof Collection<?> collection) {
            val parsed = new short[collection.size()];
            var size = 0;
            for (val object : collection) {
                try {
                    parsed[size++] = Short.parseShort(String.valueOf(object));
                } catch (NumberFormatException ignored) {}
            }
            return Optional.of(size == collection.size()
                ? parsed
                : Arrays.copyOf(parsed, size)
            );
        } else if (o.getClass().isArray()) {
            if (o.getClass().componentType() == short.class) {
                return Optional.of((short[]) o);
            }
            val sizeOriginal = Array.getLength(o);
            var size = 0;
            val parsed = new short[sizeOriginal];
            for (int i = 0; i < sizeOriginal; i++) {
                try {
                    parsed[size++] = Short.parseShort(String.valueOf(Array.get(o, i)));
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
