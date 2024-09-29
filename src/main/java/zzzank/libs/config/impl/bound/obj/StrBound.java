package zzzank.libs.config.impl.bound.obj;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.impl.bound.DefaultImmutableBound;

/**
 * @author ZZZank
 */
public class StrBound extends DefaultImmutableBound<String> {
    public static final StrBound DEFAULT_EMPTY = new StrBound("");

    private final boolean strictCheck;

    /**
     * {@code strictCheck} is enabled for this constructor
     */
    public StrBound(@NotNull String defaultValue) {
        this(defaultValue, true);
    }

    /**
     * @param strictCheck if {@code false}, every non-null value will be considered as a valid value,
     *                    and will be adapted to {@link String} via {@link Object#toString()}. Otherwise,
     *                    the value must be {@link CharSequence} to pass {@link StrBound#test(Object)}
     */
    public StrBound(@NotNull String defaultValue, boolean strictCheck) {
        super(defaultValue);
        this.strictCheck = strictCheck;
    }

    @Override
    public boolean test(Object value) {
        return strictCheck ? value instanceof CharSequence : value != null;
    }

    @Override
    public String adapt(Object value) {
        return test(value) ? value.toString() : defaultValue;
    }
}
