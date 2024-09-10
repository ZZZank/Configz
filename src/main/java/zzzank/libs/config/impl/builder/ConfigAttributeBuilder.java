package zzzank.libs.config.impl.builder;

import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigEntry;

/**
 * @author ZZZank
 */
@Setter
@Accessors(chain = true)
public class ConfigAttributeBuilder {
    public static ConfigAttributeBuilder of() {
        return new ConfigAttributeBuilder();
    }

    public boolean requiresMcRestart = false;
    public boolean requiresWorldRestart = false;
    public String displayName = null;
    public String langLey = null;
    public String[] comments = null;

    public ConfigAttribute build(ConfigEntry<?> entry) {
        val comment = comments == null || comments.length == 0
            ? null
            : String.join("\n", comments);
        if (displayName == null) {
            displayName = entry.getName();
        }
        if (langLey == null) {
            //todo: compute lang key from cfg entry side
        }
        return new ConfigAttribute(
            requiresMcRestart,
            requiresWorldRestart,
            displayName,
            langLey,
            comment
        );
    }
}
