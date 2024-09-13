package zzzank.libs.config.natived;

import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.natived.sync.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author ZZZank
 */
public interface SyncListeners {

    Map<Class<?>, BiFunction<Field, Object, FieldSyncListener<?>>> PRIMITIVES = ImmutableMap
        .<Class<?>, BiFunction<Field, Object, FieldSyncListener<?>>>builder()
        .put(int.class, IntPrimitiveSyncListener::new)
        .put(byte.class, BytePrimitiveSyncListener::new)
        .put(char.class, CharPrimitiveSyncListener::new)
        .put(short.class, ShortPrimitiveSyncListener::new)
        .put(long.class, LongPrimitiveSyncListener::new)
        .put(float.class, FloatPrimitiveSyncListener::new)
        .put(double.class, DoublePrimitiveSyncListener::new)
        .build();

    static FieldSyncListener<?> ofPrimitive(@NotNull Field field, Object instance) {
        val fn = PRIMITIVES.get(field.getType());
        if (fn == null) {
            //todo: more info
            throw new IllegalArgumentException();
        }
        return fn.apply(field, instance);
    }

    static <T> FieldSyncListener<T> ofObject(Field field, Object instance, Class<T> expectedType) {
        return new ObjectSyncListener<>(field, instance, expectedType);
    }

    static <T> FieldSyncListener<T> of(Field field, Object instance, Class<T> expectedType) {
        var fn = PRIMITIVES.get(expectedType);
        if (fn == null) {
            return ofObject(field, instance, expectedType);
        }
        return (FieldSyncListener<T>) fn.apply(field, instance);
    }
}
