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
public class FloatArrayBound extends UnifiedBound<float[]> {
    public FloatArrayBound(float @NotNull [] defaultValue) {
        super(defaultValue);
    }

    @Override
    public float @NotNull [] provideDefault() {
        return Arrays.copyOf(defaultValue, defaultValue.length);
    }

    @Override
    public Optional<float[]> tryAdapt(Object o) {
        if (o == null) {
            return Optional.empty();
        } else if (o instanceof Collection<?> collection) {
            val parsed = new float[collection.size()];
            var size = 0;
            for (val object : collection) {
                try {
                    parsed[size++] = Float.parseFloat(String.valueOf(object));
                } catch (NumberFormatException ignored) {}
            }
            return Optional.of(size == collection.size()
                ? parsed
                : Arrays.copyOf(parsed, size)
            );
        } else if (o.getClass().isArray()) {
            if (o.getClass().componentType() == float.class) {
                return Optional.of((float[]) o);
            }
            val sizeOriginal = Array.getLength(o);
            var size = 0;
            val parsed = new float[sizeOriginal];
            for (int i = 0; i < sizeOriginal; i++) {
                try {
                    parsed[size++] = Float.parseFloat(String.valueOf(Array.get(o, i)));
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
