package zzzank.libs.config.impl.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigAttribute;
import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.entry.ConfigRoot;
import zzzank.libs.config.api.save.SaveFormat;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.impl.builder.ConfigRootBuilder;

import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZZZank
 */
public class DefaultConfigRoot extends DefaultConfigCategory implements ConfigRoot {
    private final Class<?> identifier;
    private final Path path;
    private final SaveFormat format;

    public static ConfigRootBuilder builder() {
        return new ConfigRootBuilder();
    }

    public DefaultConfigRoot(
        Class<?> identifier,
        String name,
        ConfigBound<Map<String, ConfigEntry<?>>> bound,
        ConfigAttribute attribute,
        Path path,
        SaveFormat format
    ) {
        super(null, name, bound, attribute);
        this.identifier = Objects.requireNonNull(identifier);
        this.path = Objects.requireNonNull(path);
        this.format = Objects.requireNonNull(format);
    }

    @Override
    public @NotNull Path getSavePath() {
        return path;
    }

    @Override
    public SaveFormat getDefaultFormat() {
        return format;
    }

    @Override
    public void save(@NotNull Path path, @NotNull SaveFormat format) {

    }

    @Override
    public @NotNull Class<?> getIdentifier() {
        return identifier;
    }
}
