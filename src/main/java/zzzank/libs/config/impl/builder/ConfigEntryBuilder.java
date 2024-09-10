package zzzank.libs.config.impl.builder;

import lombok.Setter;
import lombok.experimental.Accessors;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.impl.bound.DefaultOnlyBound;

/**
 * @author ZZZank
 */
@Setter
@Accessors(chain = true)
public class ConfigEntryBuilder<T> {
    public static <T> ConfigEntryBuilder<T> of(ConfigBound<T> bound) {
        return new ConfigEntryBuilder<T>().setBound(bound);
    }

    public static <T> ConfigEntryBuilder<T> of(T defaultValue) {
        return new ConfigEntryBuilder<T>().setBound(new DefaultOnlyBound<>(defaultValue));
    }

    public ConfigCategory parent;
    public String name;
    public ConfigBound<T> bound;
    public ConfigAttribute attribute;
}
