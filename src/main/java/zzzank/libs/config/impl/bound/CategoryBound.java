package zzzank.libs.config.impl.bound;

import zzzank.libs.config.api.entry.ConfigEntry;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class CategoryBound extends DefaultBound<Map<String, ConfigEntry<?>>> {
    public static CategoryBound of() {
        return new CategoryBound();
    }

    public CategoryBound() {
        super(new LinkedHashMap<>());
    }

    @Override
    public boolean test(Object value) {
        return false;
    }

    @Override
    public Map<String, ConfigEntry<?>> adapt(Object value) {
        return defaultValue;
    }
}
