package zzzank.libs.config.impl.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.List;

/**
 * @author ZZZank
 */
public class DefaultConfigEntry<T> extends AbstractConfigEntry<T> {
    private T value;

    public DefaultConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<T> bound,
        List<String> comments
    ) {
        super(parent, name, bound, comments);
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
