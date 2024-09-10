package zzzank.libs.config.impl.builder;

import lombok.Setter;
import lombok.experimental.Accessors;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.impl.bound.CategoryBound;
import zzzank.libs.config.impl.entry.DefaultConfigCategory;

/**
 * @author ZZZank
 */
@Setter
@Accessors(chain = true)
public class ConfigCategoryBuilder {
    public static ConfigCategoryBuilder of() {
        return new ConfigCategoryBuilder();
    }

    public ConfigCategory parent;
    public String name;
    // public ConfigBound<T> bound;
    public ConfigAttribute attribute;

    public DefaultConfigCategory build() {
        return new DefaultConfigCategory(
            parent,
            name,
            new CategoryBound(),
            attribute
        );
    }
}
