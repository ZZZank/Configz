package zzzank.libs.config.natived.sync;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class ObjectSyncListener<T> extends FieldSyncListener<T> {

    public ObjectSyncListener(Field field, Object instance, Class<T> expectedType) {
        super(field, instance, expectedType);
    }

    @Override
    public @NotNull T preGet(ConfigEntry<T> entry, T valueToBeReturned) {
        try {
            return (T) field.get(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<T> entry, T oldValue, T newValue) {
        try {
            field.set(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
