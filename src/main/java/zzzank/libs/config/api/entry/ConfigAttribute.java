package zzzank.libs.config.api.entry;

import zzzank.libs.config.impl.builder.ConfigAttributeBuilder;

/**
 * @author ZZZank
 */
public record ConfigAttribute(
    boolean requiresMcRestart,
    boolean requiresWorldRestart,
    String displayName,
    String langLey,
    String comment
) {
    public static ConfigAttributeBuilder builder() {
        return new ConfigAttributeBuilder();
    }

    public ConfigAttribute(String name) {
        this(false, false, name, null, null);
    }
}
