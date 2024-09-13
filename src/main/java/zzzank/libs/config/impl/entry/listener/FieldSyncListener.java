package zzzank.libs.config.impl.entry.listener;

import zzzank.libs.config.api.entry.ConfigListener;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author ZZZank
 */
public abstract class FieldSyncListener<T> implements ConfigListener<T> {

    protected final Field field;
    protected final Object instance;

    public FieldSyncListener(Field field, Object instance) {
        this.field = Objects.requireNonNull(field);
        this.instance = instance;
    }
}
