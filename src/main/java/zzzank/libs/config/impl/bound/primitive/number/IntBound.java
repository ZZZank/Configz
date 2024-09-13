package zzzank.libs.config.impl.bound.primitive.number;

import zzzank.libs.config.api.bound.number.IntConfigBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;

/**
 * @author ZZZank
 */
public class IntBound extends AbstractRangedBound<Integer> implements IntConfigBound {

    public IntBound(Integer defaultValue, Integer min, Integer max) {
        super(defaultValue, min, max);
    }

    public IntBound(Integer defaultValue) {
        this(defaultValue, null, null);
    }

    @Override
    public boolean test(Object o) {
        return o instanceof Number || o instanceof Boolean || o instanceof String || o instanceof Character;
    }

    @Override
    public Integer adapt(Object value) {
        return switch (value) {
            case Number num -> num.intValue();
            case Boolean b -> b ? 1 : 0;
            case String str -> {
                try {
                    yield Integer.valueOf(str);
                } catch (NumberFormatException e) {
                    yield defaultValue;
                }
            }
            case null, default -> defaultValue;
        };
    }
}
