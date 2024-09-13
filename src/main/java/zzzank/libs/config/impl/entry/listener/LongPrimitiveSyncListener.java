package zzzank.libs.config.impl.entry.listener;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class LongPrimitiveSyncListener extends FieldSyncListener<Long> {

    public LongPrimitiveSyncListener(Field field, Object instance) {
        super(field, instance);
    }

    @Override
    public @NotNull Long preGet(ConfigEntry<Long> entry, Long valueToBeReturned) {
        try {
            return field.getLong(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<Long> entry, Long oldValue, Long newValue) {
        try {
            field.setLong(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
