package zzzank.libs.config.impl.bound.number;

import zzzank.libs.config.api.bound.number.DoubleConfigBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;

/**
 * @author ZZZank
 */
public class DoubleBound extends AbstractRangedBound<Double> implements DoubleConfigBound {
    public DoubleBound(Double defaultValue, Double min, Double max) {
        super(defaultValue, min, max);
    }
}
