package zzzank.libs.config.impl.bound;

import zzzank.libs.config.api.bound.RangedConfigBound;

/**
 * @author ZZZank
 */
public abstract class AbstractRangedBound<T extends Number> extends DefaultBound<T> implements RangedConfigBound<T> {
    private final T min;
    private final T max;

    public AbstractRangedBound(T defaultValue, T min, T max) {
        super(defaultValue);
        this.min = min;
        this.max = max;
    }

    @Override
    public T getMin() {
        return min;
    }

    @Override
    public T getMax() {
        return max;
    }
}
