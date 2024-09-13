package zzzank.libs.config.impl.entry.listener;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class IntPrimitiveSyncListener extends FieldSyncListener<Integer> {

    public IntPrimitiveSyncListener(Field field, Object instance) {
        super(field, instance);
    }

    @Override
    public @NotNull Integer preGet(ConfigEntry<Integer> entry, Integer valueToBeReturned) {
        try {
            return field.getInt(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<Integer> entry, Integer oldValue, Integer newValue) {
        try {
            field.setInt(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
