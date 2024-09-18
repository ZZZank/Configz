package zzzank.libs.config.api.entry;

import zzzank.libs.config.impl.builder.ConfigAttributeBuilder;

/**
 * @author ZZZank
 * @param external {@code true} if this config is added by reading config file
 */
public record ConfigAttribute(
    boolean requiresMcRestart,
    boolean requiresWorldRestart,
    boolean hasSlidingControl,
    String displayName,
    String langLey,
    String comment,
    boolean external
) {
    public static ConfigAttributeBuilder builder() {
        return new ConfigAttributeBuilder();
    }

    public ConfigAttribute(String name) {
        this(false, false, false, name, null, null, false);
    }
}
