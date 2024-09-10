package zzzank.libs.config.impl.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.List;
import java.util.Objects;

/**
 * @author ZZZank
 */
public abstract class AbstractConfigEntry<T> implements ConfigEntry<T> {
    protected final ConfigCategory parent;
    protected final String name;
    protected final ConfigBound<T> bound;
    protected final ConfigAttribute attribute;

    public AbstractConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<T> bound,
        ConfigAttribute attribute
    ) {
        this.parent = parent;
        this.name = Objects.requireNonNull(name);
        this.bound = Objects.requireNonNull(bound);
        this.attribute = Objects.requireNonNull(attribute);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    @NotNull
    public ConfigAttribute getAttribute() {
        return attribute;
    }

    @Override
    public @NotNull ConfigBound<T> getBound() {
        return bound;
    }

    @Override
    public ConfigCategory getParent() {
        return parent;
    }
}
