package zzzank.libs.config.api.bound;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * @author ZZZank
 */
public interface ConfigBound<T> {

    @NotNull
    T getDefault();

    /**
     * true if {@code value} can be adapted, otherwise false
     */
    boolean test(Object value);

    /**
     * if calling {@link ConfigBound#test(Object)} returns {@code false} for provided object,
     * this method is guaranteed to return what {@link ConfigBound#getDefault()} returns
     */
    T adapt(Object value);
}
