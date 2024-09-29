package zzzank.libs.config.impl.bound;

import java.util.Objects;

/**
 * helper class for
 *
 * @author ZZZank
 */
public class AdaptOnlyBound<T> extends DefaultBound<T> {
    private final Adapter<T> adapter;

    public AdaptOnlyBound(T defaultValue, Adapter<T> adapter) {
        super(defaultValue);
        this.adapter = Objects.requireNonNull(adapter);
    }

    public AdaptOnlyBound(SafedAdapter<T> adapter) {
        this(null, adapter);
    }

    @Override
    public T adapt(Object value) {
        try {
            return adapter.apply(value);
        } catch (Exception e) {
            return provideDefault();
        }
    }

    public interface Adapter<T> {
        /**
         * any exception thrown will make the {@link AdaptOnlyBound} that holds the adapter return {@link AdaptOnlyBound#provideDefault()}
         */
        T apply(Object value) throws Exception;
    }

    public interface SafedAdapter<T> extends Adapter<T> {
        @Override
        T apply(Object value);
    }
}
