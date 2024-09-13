package zzzank.libs.config.natived.sync;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class BytePrimitiveSyncListener extends FieldSyncListener<Byte> {

    public BytePrimitiveSyncListener(Field field, Object instance) {
        super(field, instance, byte.class);
    }

    @Override
    public @NotNull Byte preGet(ConfigEntry<Byte> entry, Byte valueToBeReturned) {
        try {
            return field.getByte(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<Byte> entry, Byte oldValue, Byte newValue) {
        try {
            field.setByte(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
