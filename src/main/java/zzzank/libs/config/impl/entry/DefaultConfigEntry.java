package zzzank.libs.config.impl.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.api.entry.ConfigListener;

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
        ConfigAttribute attribute,
        List<ConfigListener<T>> listeners
    ) {
        super(parent, name, bound, attribute, listeners);
        this.value = bound.getDefault();
    }

    @Override
    @NotNull
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(@NotNull T value) {
        this.value = value;
    }
}
