package zzzank.libs.config.impl.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.entry.ConfigCategory;
import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.entry.ConfigRoot;
import zzzank.libs.config.api.save.SaveFormat;
import zzzank.libs.config.api.bound.ConfigBound;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZZZank
 */
public class DefaultConfigRoot extends DefaultConfigCategory implements ConfigRoot {
    private final Class<?> identifier;
    private final Path path;

    public DefaultConfigRoot(
        Class<?> identifier,
        String name,
        ConfigBound<Map<String, ConfigEntry<?>>> bound,
        List<String> comments,
        Path path
    ) {
        super(null, name, bound, comments);
        this.identifier = Objects.requireNonNull(identifier);
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public @NotNull Path getSavePath() {
        return path;
    }

    @Override
    public void save(@NotNull SaveFormat format) {

    }

    @Override
    public @NotNull Class<?> getIdentifier() {
        return identifier;
    }
}
