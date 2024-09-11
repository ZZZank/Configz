package zzzank.libs.config.impl.entry.number;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.impl.entry.AbstractConfigEntry;

import java.util.List;

/**
 * @author ZZZank
 */
public class IntConfigEntry extends AbstractConfigEntry<Integer> {
    private int value;

    public IntConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<Integer> bound,
        ConfigAttribute attribute
    ) {
        super(parent, name, bound, attribute);
    }

    @Override
    public @NotNull Integer get() {
        return value;
    }

    public int getRaw() {
        return value;
    }

    @Override
    protected void setValue(@NotNull Integer newValue) {
        this.value = newValue;
    }
}
