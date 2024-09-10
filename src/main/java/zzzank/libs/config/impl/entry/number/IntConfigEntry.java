package zzzank.libs.config.impl.entry.number;

import org.jetbrains.annotations.NotNull;
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
        List<String> comments
    ) {
        super(parent, name, bound, comments);
    }

    @Override
    public @NotNull Integer get() {
        return value;
    }

    public int getRaw() {
        return value;
    }

    public void set(int newValue) {
    }

    @Override
    public void set(@NotNull Integer newValue) {
        set(newValue.intValue());
    }
}
