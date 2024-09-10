package zzzank.libs.config.impl.bound;

import zzzank.libs.config.api.entry.ConfigEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class CategoryBound extends DefaultOnlyBound<Map<String, ConfigEntry<?>>> {
    public static CategoryBound of() {
        return new CategoryBound();
    }

    public CategoryBound() {
        super(new HashMap<>());
    }
}
