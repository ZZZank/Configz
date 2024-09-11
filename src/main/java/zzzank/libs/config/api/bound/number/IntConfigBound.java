package zzzank.libs.config.api.bound.number;

import zzzank.libs.config.api.bound.RangedConfigBound;

/**
 * @author ZZZank
 */
public interface IntConfigBound extends RangedConfigBound<Integer> {

    @Override
    default boolean test(Object value) {
        return value instanceof Number number
            && (getMin() == null || getMin() <= number.doubleValue())
            && (getMax() == null || getMax() >= number.doubleValue());
    }
}
