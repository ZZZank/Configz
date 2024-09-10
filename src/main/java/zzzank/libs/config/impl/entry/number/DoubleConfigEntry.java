package zzzank.libs.config.impl.entry.number;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.impl.entry.AbstractConfigEntry;

import java.util.List;

/**
 * @author ZZZank
 */
public class DoubleConfigEntry extends AbstractConfigEntry<Double> {
    private double value;

    public DoubleConfigEntry(
        ConfigCategory parent,
        String name,
        ConfigBound<Double> bound,
        List<String> comments
    ) {
        super(parent, name, bound, comments);
    }

    @Override
    public @NotNull Double get() {
        return value;
    }

    public double getRaw() {
        return value;
    }

    public void set(double newValue) {
    }

    @Override
    public void set(@NotNull Double newValue) {
        set(newValue.intValue());
    }
}
