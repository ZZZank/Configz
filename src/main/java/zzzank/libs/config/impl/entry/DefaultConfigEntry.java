package zzzank.libs.config.impl.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.bound.ConfigBound;

/**
 * @author ZZZank
 */
public class DefaultConfigEntry<T> extends AbstractConfigEntry<T> {
    private T value;

    public DefaultConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<T> bound
    ) {
        super(parent, name, bound, new ConfigAttribute(name));
        this.value = bound.getDefault();
    }

    @Override
    public @NotNull T get() {
        return value;
    }

    @Override
    public void set(@NotNull T newValue) {
        this.value = bound.adapt(newValue);
    }
}
