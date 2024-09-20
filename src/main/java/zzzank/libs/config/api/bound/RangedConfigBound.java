package zzzank.libs.config.api.bound;

import org.jetbrains.annotations.Nullable;

/**
 * @author ZZZank
 */
public interface RangedConfigBound<T extends Number> extends ConfigBound<T> {

    @Nullable
    T getMin();

    @Nullable
    T getMax();

    @Override
    default boolean test(Object value) {
        return value instanceof Number number
            && (getMin() == null || getMin().doubleValue() <= number.doubleValue())
            && (getMax() == null || getMax().doubleValue() <= number.doubleValue());
    }
}
