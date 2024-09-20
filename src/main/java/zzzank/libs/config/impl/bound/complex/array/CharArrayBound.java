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
public class CharArrayBound extends UnifiedBound<char[]> {
    public CharArrayBound(char @NotNull [] defaultValue) {
        super(defaultValue);
    }

    @Override
    public Optional<char[]> tryAdapt(Object o) {
        if (o == null) {
            return Optional.empty();
        } else if (o instanceof Collection<?> collection) {
            val parsed = new char[collection.size()];
            var size = 0;
            for (val object : collection) {
                val str = String.valueOf(object);
                if (str.length() > 1) {
                    continue;
                }
                try {
                    parsed[size++] = str.charAt(0);
                } catch (NumberFormatException ignored) {
                }
            }
            return Optional.of(size == collection.size()
                ? parsed
                : Arrays.copyOf(parsed, size)
            );
        } else if (o.getClass().isArray()) {
            if (o.getClass().componentType() == char.class) {
                return Optional.of((char[]) o);
            }
            val sizeOriginal = Array.getLength(o);
            var size = 0;
            val parsed = new char[sizeOriginal];
            for (int i = 0; i < sizeOriginal; i++) {
                val str = String.valueOf(Array.get(o, i));
                if (str.length() > 1) {
                    continue;
                }
                try {
                    parsed[size++] = str.charAt(0);
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
