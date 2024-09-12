package zzzank.libs.config.impl.bound.complex;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author ZZZank
 */
public class MapBound<T extends Map<?, ?>> implements ConfigBound<T> {
    private final Supplier<T> supplier;
    private final BiConsumer<T, Object> accumulator;

    public MapBound(Supplier<T> supplier, BiConsumer<T, Object> accumulator) {
        this.supplier = supplier;
        this.accumulator = accumulator;
    }

    public <K, V> MapBound(
        Supplier<T> supplier,
        BiConsumer<T, Map.Entry<K, V>> accumulator,
        ConfigBound<Map.Entry<K, V>> entryBound
    ) {
        this(supplier, (c, o) -> accumulator.accept(c, entryBound.adapt(o)));
    }

    public <K, V> MapBound(
        Supplier<T> supplier,
        BiConsumer<T, Map.Entry<K, V>> accumulator,
        ConfigBound<K> keyBound,
        ConfigBound<V> valueBound
    ) {
        this(
            supplier,
            (collection, obj) -> accumulator.accept(
                collection,
                new AbstractMap.SimpleImmutableEntry<>(keyBound.adapt(obj), valueBound.adapt(obj))
            )
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
