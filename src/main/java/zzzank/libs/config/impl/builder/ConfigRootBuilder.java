package zzzank.libs.config.impl.builder;

import lombok.Setter;
import lombok.experimental.Accessors;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.file.FileFormat;
import zzzank.libs.config.impl.bound.CategoryBound;
import zzzank.libs.config.impl.entry.DefaultConfigRoot;

import java.nio.file.Path;

/**
 * @author ZZZank
 */
@Setter
@Accessors(chain = true)
public class ConfigRootBuilder {
    public String name;
    public ConfigAttribute attribute;
    public Class<?> identifier;
    public Path path;
    public FileFormat format;

    public static ConfigRootBuilder of() {
        return new ConfigRootBuilder();
    }

    public DefaultConfigRoot build() {
        return new DefaultConfigRoot(
            identifier,
            name,
            new CategoryBound(),
            attribute,
            path,
            format
        );
    }
}
