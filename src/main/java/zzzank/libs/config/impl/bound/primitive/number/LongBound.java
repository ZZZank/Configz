package zzzank.libs.config.impl.bound.primitive.number;

import zzzank.libs.config.api.bound.UnifiedTestAdaptBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;
import zzzank.libs.config.utils.Toolkit;

import java.util.Optional;

/**
 * @author ZZZank
 */
public class LongBound
    extends AbstractRangedBound<Long>
    implements UnifiedTestAdaptBound<Long> {
    public static final LongBound DEFAULT_ZERO = new LongBound((long) 0);
    public static final LongBound DEFAULT_NEG1 = new LongBound((long) -1);
    public static final LongBound DEFAULT_MIN = new LongBound(Long.MIN_VALUE);
    public static final LongBound DEFAULT_MAX = new LongBound(Long.MAX_VALUE);

    public LongBound(Long defaultValue, Long min, Long max) {
        super(defaultValue, min, max);
    }

    public LongBound(Long defaultValue) {
        this(defaultValue, null, null);
    }

    @Override
    public Optional<Long> tryAdapt(Object o) {
        return switch (o) {
            case Number num -> super.test(num) ? Optional.of(num.longValue()) : Optional.empty();
            case Boolean b -> Optional.of((long) (b ? 1 : 0));
            case String str -> Toolkit.tryOr(() -> Optional.of(Long.valueOf(str)), Optional.empty());
            case null, default -> Optional.empty();
        };
    }
}
