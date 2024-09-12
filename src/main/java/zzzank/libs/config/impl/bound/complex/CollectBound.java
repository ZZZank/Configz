package zzzank.libs.config.impl.bound.complex;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author ZZZank
 */
public class CollectBound<T extends Collection<?>> implements ConfigBound<T> {
    private final Supplier<T> supplier;
    private final BiConsumer<T, Object> accumulator;

    public CollectBound(Supplier<T> supplier, BiConsumer<T, Object> accumulator) {
        this.supplier = supplier;
        this.accumulator = accumulator;
    }

    public <E> CollectBound(Supplier<T> supplier, BiConsumer<T, E> accumulator, ConfigBound<E> elementBound) {
        this(
            supplier,
            (collection, obj) -> accumulator.accept(collection, elementBound.adapt(obj))
        );
    }

    @Override
    public @NotNull T getDefault() {
        return supplier.get();
    }

    @Override
    public boolean test(Object value) {
        return value instanceof Iterable<?>;
    }

    @Override
    public @NotNull T adapt(Object value) {
        val adapted = supplier.get();
        if (!test(value)) {
            return getDefault();
        }
        for (val o : (Iterable<?>) value) {
            accumulator.accept(adapted, o);
        }
        return adapted;
    }
}
