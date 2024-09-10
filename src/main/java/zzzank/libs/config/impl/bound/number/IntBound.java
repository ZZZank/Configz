package zzzank.libs.config.impl.bound.number;

import zzzank.libs.config.api.bound.number.IntConfigBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;

/**
 * @author ZZZank
 */
public class IntBound extends AbstractRangedBound<Integer> implements IntConfigBound {
    public IntBound(Integer defaultValue, Integer min, Integer max) {
        super(defaultValue, min, max);
    }
}
