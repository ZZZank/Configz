package zzzank.libs.config.impl.entry.listener;

import zzzank.libs.config.api.entry.ConfigListener;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author ZZZank
 */
public abstract class FieldSyncListener<T> implements ConfigListener<T> {

    public final Field field;
    public final Object instance;
    public final Class<T> expectedType;

    public FieldSyncListener(Field field, Object instance, Class<T> expectedType) {
        this.field = Objects.requireNonNull(field);
        this.instance = instance;
        this.expectedType = Objects.requireNonNull(expectedType);
        if (expectedType != field.getType()) {
            throw new IllegalArgumentException("expecting a field with '%s' type, but got '%s'".formatted(
                expectedType, field.getType()
            ));
        }
    }
}
