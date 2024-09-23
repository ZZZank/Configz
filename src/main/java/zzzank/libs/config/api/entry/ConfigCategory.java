package zzzank.libs.config.api.entry;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zzzank.libs.config.impl.builder.ConfigEntryBuilder;

import java.util.Map;

/**
 * @author ZZZank
 */
public interface ConfigCategory extends ConfigEntry<Map<String, ConfigEntry<?>>> {

    @Deprecated
    @Override
    void set(@NotNull Map<String, ConfigEntry<?>> newValue);

    @Deprecated
    @Override
    @NotNull Map<String, ConfigEntry<?>> get();

    default <T> ConfigEntry<T> addEntry(ConfigEntryBuilder<T> builder) {
        return addEntry(builder.setParent(this).build());
    }

    default <T> ConfigEntry<T> addEntry(ConfigEntry<T> entry) {
        if (entry == null || entry.getParent() != this) {
            throw new IllegalArgumentException("config entry invalid");
        }
        if (this.get().containsKey(entry.getName())) {
            throw new IllegalArgumentException("config entry with name '%s' already existed".formatted(entry.getName()));
        }
        this.get().put(entry.getName(), entry);
        return entry;
    }

    @Nullable
    default ConfigEntry<?> get(String name) {
        if (name == null) {
            return null;
        }
        val parts = name.split("\\.");
        if (parts.length == 1) {
            return get().get(name);
        }
        ConfigEntry<?> entry = this;
        for (val part : parts) {
            entry = entry.asCategory().get(part);
        }
        return entry;
    }

    default boolean isCategory() {
        return true;
    }
}
