package zzzank.libs.config.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigRoot;
import zzzank.libs.config.natived.ConfigGenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class ConfigManager {

    /**
     * name -> config root
     */
    private static final Map<String, ConfigRoot> ROOTS_BY_NAME = new HashMap<>();
    /**
     * modid -> config root
     */
    private static final Multimap<String, ConfigRoot> ROOTS_BY_MOD = HashMultimap.create();

    private static final Table<Class<?>, String, ConfigRoot> TABLE = HashBasedTable.create();
    public static final String DEFAULT_CATEGORY = "general";

    public static @NotNull ConfigRoot register(Class<?> target) {
        return register(ConfigGenerator.scan(target));
    }

    public static @NotNull ConfigRoot register(@NotNull ConfigRoot root) {
        if (ROOTS_BY_NAME.containsKey(root.getName())) {
            throw new IllegalStateException("config with name '%s' already registered".formatted(root.getName()));
        }
        ROOTS_BY_NAME.put(root.getName(), root);
        return root;
    }

    public static Collection<ConfigRoot> get(String modid) {
        return ROOTS_BY_MOD.get(modid);
    }

    public static ConfigRoot getByFileName(String name) {
        return ROOTS_BY_NAME.get(name);
    }

    /**
     * ?
     */
    public static ConfigRoot get(String modid, String category) {
        return ROOTS_BY_MOD.get(modid)
            .stream()
            .filter(root -> root.getName().equals(category))
            .findAny()
            .orElse(null);
    }
}
