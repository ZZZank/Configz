package zzzank.libs.config.api.bound;

import org.jetbrains.annotations.Nullable;

/**
 * @author ZZZank
 */
public interface RangedConfigBound<T extends Number> extends ConfigBound<T> {

    @Nullable
    T getMin();

    @Nullable
    T getMax();
}
