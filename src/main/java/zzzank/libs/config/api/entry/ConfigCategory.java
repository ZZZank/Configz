package zzzank.libs.config.api.entry;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author ZZZank
 */
public interface ConfigCategory extends ConfigEntry<Map<String, ConfigEntry<?>>> {

    @Nullable
    default ConfigEntry<?> get(String name) {
        if (name == null) {
            return null;
        }
        var parts = name.split("\\.");
        if (parts.length == 1) {
            return get().get(name);
        }
        ConfigEntry<?> entry = this;
        for (var part : parts) {
            entry = entry.asCategory().get(part);
        }
        return entry;
    }

    default boolean isCategory() {
        return true;
    }
}
