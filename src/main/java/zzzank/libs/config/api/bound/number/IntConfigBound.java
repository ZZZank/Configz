package zzzank.libs.config.api.bound.number;

import zzzank.libs.config.api.bound.RangedConfigBound;

/**
 * @author ZZZank
 */
public interface IntConfigBound extends RangedConfigBound<Integer> {

    @Override
    default boolean test(Integer value) {
        return value != null
            && (getMin() == null || getMin() <= value)
            && (getMax() == null || getMax() >= value);
    }
}
