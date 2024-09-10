package zzzank.libs.config.impl.entry;

import org.jetbrains.annotations.NotNull;
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
    protected final List<String> comments;

    public AbstractConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<T> bound,
        List<String> comments
    ) {
        this.parent = parent;
        this.name = Objects.requireNonNull(name);
        this.bound = Objects.requireNonNull(bound);
        this.comments = Objects.requireNonNull(comments);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull List<String> getComments() {
        return comments;
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
