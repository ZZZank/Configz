package zzzank.libs.config.impl.entry.listener;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigEntry;

import java.lang.reflect.Field;

/**
 * @author ZZZank
 */
public class CharPrimitiveSyncListener extends FieldSyncListener<Character> {

    public CharPrimitiveSyncListener(Field field, Object instance) {
        super(field, instance);
    }

    @Override
    public @NotNull Character preGet(ConfigEntry<Character> entry, Character valueToBeReturned) {
        try {
            return field.getChar(instance);
        } catch (IllegalAccessException e) {
            return valueToBeReturned;
        }
    }

    @Override
    public void postSet(ConfigEntry<Character> entry, Character oldValue, Character newValue) {
        try {
            field.setChar(instance, newValue);
        } catch (IllegalAccessException ignored) {
        }
    }
}
