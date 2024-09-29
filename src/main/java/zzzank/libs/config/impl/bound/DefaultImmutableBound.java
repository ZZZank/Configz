package zzzank.libs.config.impl.bound;

import org.jetbrains.annotations.NotNull;

/**
 *
 * @author ZZZank
 */
public class DefaultImmutableBound<T> extends DefaultBound<T> {

    public DefaultImmutableBound(@NotNull T defaultValue) {
        super(defaultValue);
    }

    @Override
    public @NotNull T provideDefault() {
        return defaultValue;
    }
}
