package zzzank.libs.config.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import zzzank.libs.config.api.entry.ConfigRoot;
import zzzank.libs.config.natived.ClassBasedConfigGenerator;

/**
 * @author ZZZank
 */
public class ConfigManager {

    private static final Table<Class<?>, String, ConfigRoot> TABLE = HashBasedTable.create();
    public static final String DEFAULT_CATEGORY = "general";

    public static ConfigRoot register(Class<?> target) {
        return register(target, ClassBasedConfigGenerator.scan(target));
    }

    public static ConfigRoot register(Class<?> identifier, ConfigRoot root) {
        if (TABLE.contains(identifier, root.getName())) {
            throw new IllegalStateException("already registered");
        }
        TABLE.put(identifier, root.getName(), root);
        return root;
    }

    public static ConfigRoot get(Class<?> identifier) {
        return TABLE.get(identifier, DEFAULT_CATEGORY);
    }

    public static ConfigRoot get(Class<?> identifier, String category) {
        return TABLE.get(identifier, category);
    }
}
