package zzzank.libs.config.impl.bound.primitive;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.impl.bound.DefaultBound;

/**
 * @author ZZZank
 */
public class CharBound extends DefaultBound<Character> {
    public static final CharBound DEFAULT_MAX = new CharBound(Character.MAX_VALUE);
    public static final CharBound DEFAULT_MIN = new CharBound(Character.MIN_VALUE);

    public CharBound(@NotNull Character defaultValue) {
        super(defaultValue);
    }
}
