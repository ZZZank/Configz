package zzzank.libs.config.impl.entry;

import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.List;
import java.util.Map;

/**
 * @author ZZZank
 */
public class DefaultConfigCategory extends DefaultConfigEntry<Map<String, ConfigEntry<?>>> implements ConfigCategory {

    public DefaultConfigCategory(
        ConfigCategory parent,
        String name,
        ConfigBound<Map<String, ConfigEntry<?>>> bound,
        List<String> comments
    ) {
        super(parent, name, bound);
    }
}
