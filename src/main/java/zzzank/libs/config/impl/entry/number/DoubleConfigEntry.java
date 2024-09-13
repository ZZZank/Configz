package zzzank.libs.config.impl.entry.number;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.api.entry.ConfigAttribute;
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
        ConfigAttribute attribute
    ) {
        super(parent, name, bound, attribute);
    }

    @Override
    @NotNull
    public Double getValue() {
        return value;
    }

    public double getRaw() {
        return value;
    }

    @Override
    protected void setValue(@NotNull Double newValue) {
        this.value = newValue;
    }
}
