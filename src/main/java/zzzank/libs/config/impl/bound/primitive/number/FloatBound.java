package zzzank.libs.config.impl.bound.primitive.number;

import zzzank.libs.config.api.bound.UnifiedTestAdaptBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;
import zzzank.libs.config.utils.Toolkit;

import java.util.Optional;

/**
 * @author ZZZank
 */
public class FloatBound
    extends AbstractRangedBound<Float>
    implements UnifiedTestAdaptBound<Float> {
    public static final FloatBound DEFAULT_ZERO = new FloatBound(0.0f);
    public static final FloatBound DEFAULT_NEG1 = new FloatBound(-1f);
    public static final FloatBound DEFAULT_MIN = new FloatBound(Float.MIN_VALUE);
    public static final FloatBound DEFAULT_MAX = new FloatBound(Float.MAX_VALUE);

    public FloatBound(Float defaultValue, Float min, Float max) {
        super(defaultValue, min, max);
    }

    public FloatBound(Float defaultValue) {
        this(defaultValue, null, null);
    }

    @Override
    public Optional<Float> tryAdapt(Object o) {
        return switch (o) {
            case Number num -> super.test(num) ? Optional.of(num.floatValue()) : Optional.empty();
            case Boolean b -> Optional.of(b ? 1.0f : 0.0f);
            case String str -> Toolkit.tryOr(() -> Optional.of(Float.valueOf(str)), Optional.empty());
            case null, default -> Optional.empty();
        };
    }
}
