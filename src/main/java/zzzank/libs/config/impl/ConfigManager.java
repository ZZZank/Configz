package zzzank.libs.config.impl;

import zzzank.libs.config.api.entry.ConfigRoot;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class ConfigManager {

    private static final Map<Class<?>, ConfigRoot> ALL = new IdentityHashMap<>();

    public static ConfigRoot register(Class<?> target) {
        return register(target, scanEntries(target));
    }

    public static ConfigRoot register(Class<?> identifier, ConfigRoot root) {
        if (ALL.containsKey(identifier)) {
            throw new IllegalStateException("already registered");
        }
        ALL.put(identifier, root);
        return root;
    }

    public static ConfigRoot get(Class<?> identifier) {
        return ALL.get(identifier);
    }

    private static ConfigRoot scanEntries(Class<?> target) {
        return null;
    }
}
