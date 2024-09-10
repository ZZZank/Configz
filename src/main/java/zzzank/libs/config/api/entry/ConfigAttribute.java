package zzzank.libs.config.api.entry;

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
    public ConfigAttribute(String name) {
        this(false, false, name, null, null);
    }
}
