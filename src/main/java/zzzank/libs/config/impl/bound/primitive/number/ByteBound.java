package zzzank.libs.config.impl.bound.primitive.number;

import zzzank.libs.config.api.bound.UnifiedTestAdaptBound;
import zzzank.libs.config.impl.bound.AbstractRangedBound;
import zzzank.libs.config.utils.Toolkit;

import java.util.Optional;

/**
 * @author ZZZank
 */
public class ByteBound
    extends AbstractRangedBound<Byte>
    implements UnifiedTestAdaptBound<Byte> {
    public static final ByteBound DEFAULT_ZERO = new ByteBound((byte) 0);
    public static final ByteBound DEFAULT_NEG1 = new ByteBound((byte) -1);
    public static final ByteBound DEFAULT_MIN = new ByteBound(Byte.MIN_VALUE);
    public static final ByteBound DEFAULT_MAX = new ByteBound(Byte.MAX_VALUE);

    public ByteBound(Byte defaultValue, Byte min, Byte max) {
        super(defaultValue, min, max);
    }

    public ByteBound(Byte defaultValue) {
        this(defaultValue, null, null);
    }

    @Override
    public Optional<Byte> tryAdapt(Object o) {
        return switch (o) {
            case Number num -> super.test(num) ? Optional.of(num.byteValue()) : Optional.empty();
            case Boolean b -> Optional.of((byte)(b ? 1 : 0));
            case String str -> Toolkit.tryOr(() -> Optional.of(Byte.valueOf(str)), Optional.empty());
            case null, default -> Optional.empty();
        };
    }
}
