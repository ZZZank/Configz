package zzzank.libs.config.impl.bound.primitive.number;

import zzzank.libs.config.api.bound.UnifiedTestAdaptBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;
import zzzank.libs.config.utils.Toolkit;

import java.util.Optional;

/**
 * @author ZZZank
 */
public class ShortBound
    extends AbstractRangedBound<Short>
    implements UnifiedTestAdaptBound<Short> {
    public static final ShortBound DEFAULT_ZERO = new ShortBound((short) 0);
    public static final ShortBound DEFAULT_NEG1 = new ShortBound((short) -1);
    public static final ShortBound DEFAULT_MIN = new ShortBound(Short.MIN_VALUE);
    public static final ShortBound DEFAULT_MAX = new ShortBound(Short.MAX_VALUE);

    public ShortBound(Short defaultValue, Short min, Short max) {
        super(defaultValue, min, max);
    }

    public ShortBound(Short defaultValue) {
        this(defaultValue, null, null);
    }

    @Override
    public Optional<Short> tryAdapt(Object o) {
        return switch (o) {
            case Number num -> super.test(num) ? Optional.of(num.shortValue()) : Optional.empty();
            case Boolean b -> Optional.of((short) (b ? 1 : 0));
            case String str -> Toolkit.tryOr(() -> Optional.of(Short.valueOf(str)), Optional.empty());
            case null, default -> Optional.empty();
        };
    }
}
