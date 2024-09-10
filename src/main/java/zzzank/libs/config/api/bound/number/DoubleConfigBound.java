package zzzank.libs.config.api.bound.number;

import zzzank.libs.config.api.bound.RangedConfigBound;

/**
 * @author ZZZank
 */
public interface DoubleConfigBound extends RangedConfigBound<Double> {

    @Override
    default boolean test(Double value) {
        return value != null
            && (getMin() == null || getMin() <= value)
            && (getMax() == null || getMax() >= value);
    }
}
