package zzzank.libs.config.api.bound;

import java.util.Optional;

/**
 * @author ZZZank
 */
public interface UnifiedTestAdaptBound<T> extends ConfigBound<T> {

    @Override
    default boolean test(Object value) {
        return tryAdapt(value).isPresent();
    }

    @Override
    default T adapt(Object value) {
        return tryAdapt(value).orElse(provideDefault());
    }

    Optional<T> tryAdapt(Object o);
}
