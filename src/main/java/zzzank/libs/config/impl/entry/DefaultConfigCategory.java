package zzzank.libs.config.impl.entry;

import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.Map;

/**
 * @author ZZZank
 */
public class DefaultConfigCategory extends DefaultConfigEntry<Map<String, ConfigEntry<?>>> implements ConfigCategory {

    public DefaultConfigCategory(
        ConfigCategory parent,
        String name,
        ConfigBound<Map<String, ConfigEntry<?>>> bound,
        ConfigAttribute attribute
    ) {
        super(parent, name, bound, attribute);
    }
}
