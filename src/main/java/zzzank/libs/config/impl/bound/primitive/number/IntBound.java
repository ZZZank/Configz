package zzzank.libs.config.impl.bound.primitive.number;

import zzzank.libs.config.api.bound.UnifiedTestAdaptBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;
import zzzank.libs.config.utils.Toolkit;

import java.util.Optional;

/**
 * @author ZZZank
 */
public class IntBound
    extends AbstractRangedBound<Integer>
    implements UnifiedTestAdaptBound<Integer> {
    public static final IntBound DEFAULT_ZERO = new IntBound(0);
    public static final IntBound DEFAULT_NEG1 = new IntBound(-1);
    public static final IntBound DEFAULT_MIN = new IntBound(Integer.MIN_VALUE);
    public static final IntBound DEFAULT_MAX = new IntBound(Integer.MAX_VALUE);

    public IntBound(Integer defaultValue, Integer min, Integer max) {
        super(defaultValue, min, max);
    }

    public IntBound(Integer defaultValue) {
        this(defaultValue, null, null);
    }

    @Override
    public Optional<Integer> tryAdapt(Object o) {
        return switch (o) {
            case Number num -> super.test(num) ? Optional.of(num.intValue()) : Optional.empty();
            case Boolean b -> Optional.of(b ? 1 : 0);
            case String str -> Toolkit.tryOr(() -> Integer.valueOf(str).describeConstable(), Optional.empty());
            case null, default -> Optional.empty();
        };
    }
}
