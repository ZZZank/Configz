package zzzank.libs.config.api.bound;

import org.jetbrains.annotations.NotNull;

/**
 * @author ZZZank
 */
public interface ConfigBound<T> {

    /**
     * the return value of this method is "independent of each other", which is, return value is either immutable,
     * or a new instance that will not, when received changes, affect other return values
     *
     * @return an individual instance that represents the default value this {@link ConfigBound} holds
     */
    @NotNull
    T provideDefault();

    /**
     * true if {@code value} can be adapted, otherwise false
     */
    boolean test(Object value);

    /**
     * if calling {@link ConfigBound#test(Object)} returns {@code false} for provided object,
     * this method is guaranteed to return what {@link ConfigBound#provideDefault()} returns
     */
    T adapt(Object value);
}
