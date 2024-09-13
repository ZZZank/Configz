package zzzank.libs.config.natived.sync;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class ShortPrimitiveSyncListener extends FieldSyncListener<Short> {

    public ShortPrimitiveSyncListener(Field field, Object instance) {
        super(field, instance, short.class);
    }

    @Override
    public @NotNull Short preGet(ConfigEntry<Short> entry, Short valueToBeReturned) {
        try {
            return field.getShort(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<Short> entry, Short oldValue, Short newValue) {
        try {
            field.setShort(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
