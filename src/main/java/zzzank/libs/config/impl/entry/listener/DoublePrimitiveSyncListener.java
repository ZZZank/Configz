package zzzank.libs.config.impl.entry.listener;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class DoublePrimitiveSyncListener extends FieldSyncListener<Double> {

    public DoublePrimitiveSyncListener(Field field, Object instance) {
        super(field, instance, double.class);
    }

    @Override
    public @NotNull Double preGet(ConfigEntry<Double> entry, Double valueToBeReturned) {
        try {
            return field.getDouble(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<Double> entry, Double oldValue, Double newValue) {
        try {
            field.setDouble(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
