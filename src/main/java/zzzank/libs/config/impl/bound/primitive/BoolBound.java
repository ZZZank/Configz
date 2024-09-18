package zzzank.libs.config.impl.bound.primitive;

import zzzank.libs.config.impl.bound.DefaultBound;

/**
 * @author ZZZank
 */
public class BoolBound extends DefaultBound<Boolean> {
    public static final BoolBound DEFAULT_FALSE = new BoolBound(false);
    public static final BoolBound DEFAULT_TRUE = new BoolBound(true);

    public BoolBound(Boolean defaultValue) {
        super(defaultValue);
    }

    @Override
    public boolean test(Object value) {
        return value instanceof Boolean || value instanceof Number || value instanceof CharSequence;
    }

    @Override
    public Boolean adapt(Object value) {
        return switch (value) {
            case Boolean b -> b;
            case Number number -> !number.equals(0);
            case CharSequence seq -> Boolean.valueOf(seq.toString());
            case null, default -> defaultValue;
        };
    }
}
