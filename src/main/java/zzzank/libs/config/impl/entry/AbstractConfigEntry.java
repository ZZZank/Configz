package zzzank.libs.config.impl.entry;

import com.google.common.collect.ImmutableList;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.api.entry.ConfigListener;

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
    protected final List<ConfigListener<T>> listeners;

    public AbstractConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<T> bound,
        ConfigAttribute attribute
    ) {
        this(parent, name, bound, attribute, ImmutableList.of());
    }

    public AbstractConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<T> bound,
        ConfigAttribute attribute,
        List<ConfigListener<T>> listeners
    ) {
        this.parent = parent;
        this.name = Objects.requireNonNull(name);
        this.bound = Objects.requireNonNull(bound);
        this.attribute = Objects.requireNonNull(attribute);
        this.listeners = ImmutableList.copyOf(listeners);
    }

    @Override
    public void set(@NotNull T newValue) {
        val oldValue = getValue();
        if (newValue == oldValue || oldValue.equals(newValue)) {
            return;
        }
        if (!bound.test(newValue)) {
            //todo: log error
            setValue(bound.getDefault());
        } else {
            setValue(newValue);
        }
        for (val listener : listeners) {
            listener.postSet(this, oldValue, getValue());
        }
    }

    /**
     *
     */
    abstract protected void setValue(@NotNull T newValue);

    @Override
    public @NotNull T get() {
        T value = getValue();
        for (val listener : listeners) {
            value = listener.preGet(this, value);
        }
        set(value);
        return value;
    }

    @NotNull
    abstract protected T getValue();

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public List<ConfigListener<T>> getListeners() {
        return this.listeners;
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
