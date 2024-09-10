package zzzank.libs.config.api.bound;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * @author ZZZank
 */
public interface ConfigBound<T> extends Predicate<T> {

    @NotNull
    T getDefault();

    @Override
    boolean test(T value);

    @NotNull
    T adapt(Object value);

    default boolean isCategory() {
        return false;
    }
}
