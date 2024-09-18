package zzzank.libs.config.impl.bound;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.UnifiedTestAdaptBound;

import java.lang.ref.WeakReference;
import java.util.Optional;

/**
 * @author ZZZank
 */
public abstract class UnifiedBound<T> extends DefaultBound<T> implements UnifiedTestAdaptBound<T> {
    private WeakReference<Object> cachedRef;
    private Optional<T> cachedValue;

    public UnifiedBound(@NotNull T defaultValue) {
        super(defaultValue);
        this.cachedRef = new WeakReference<>(defaultValue);
        this.cachedValue = Optional.of(defaultValue);
    }

    @Override
    public boolean test(Object value) {
        if (!cachedRef.refersTo(value)) {
            val result = tryAdapt(value);
            cachedRef = new WeakReference<>(result);
            cachedValue = result;
        }
        return cachedValue.isPresent();
    }

    @Override
    public T adapt(Object value) {
        if (!cachedRef.refersTo(value)) {
            val result = tryAdapt(value);
            cachedRef = new WeakReference<>(result);
            cachedValue = result;
        }
        return cachedValue.orElse(defaultValue);
    }
}
