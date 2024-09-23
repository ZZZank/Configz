package zzzank.libs.config.impl.builder;

import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;
import zzzank.libs.config.api.entry.ConfigAttribute;

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
    public boolean hasSlidingControl = false;
    public String displayName = null;
    public String langLey = null;
    public String[] comments = null;
    public boolean external = false;

    public ConfigAttribute build() {
        val comment = comments == null || comments.length == 0
            ? null
            : String.join("\n", comments);
        if (langLey == null) {
            //todo: compute lang key from cfg entry side
        }
        return new ConfigAttribute(
            requiresMcRestart,
            requiresWorldRestart,
            hasSlidingControl,
            displayName,
            langLey,
            comment,
            external
        );
    }
}
