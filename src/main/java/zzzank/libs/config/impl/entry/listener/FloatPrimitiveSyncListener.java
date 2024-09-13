package zzzank.libs.config.impl.entry.listener;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class FloatPrimitiveSyncListener extends FieldSyncListener<Float> {

    public FloatPrimitiveSyncListener(Field field, Object instance) {
        super(field, instance, float.class);
    }

    @Override
    public @NotNull Float preGet(ConfigEntry<Float> entry, Float valueToBeReturned) {
        try {
            return field.getFloat(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<Float> entry, Float oldValue, Float newValue) {
        try {
            field.setFloat(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
