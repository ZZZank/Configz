package zzzank.libs.config.impl.bound.primitive.number;

import zzzank.libs.config.api.bound.number.DoubleConfigBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;

/**
 * @author ZZZank
 */
public class DoubleBound extends AbstractRangedBound<Double> implements DoubleConfigBound {
    public static final DoubleBound DEFAULT_ZERO = new DoubleBound((double) 0);
    public static final DoubleBound DEFAULT_NEG1 = new DoubleBound((double) -1);
    public static final DoubleBound DEFAULT_MIN = new DoubleBound(Double.MIN_VALUE);
    public static final DoubleBound DEFAULT_MAX = new DoubleBound(Double.MAX_VALUE);
    public static final DoubleBound DEFAULT_NAN = new DoubleBound(Double.NaN);

    public DoubleBound(Double defaultValue, Double min, Double max) {
        super(defaultValue, min, max);
    }

    public DoubleBound(Double defaultValue) {
        this(defaultValue, null, null);
    }
}
