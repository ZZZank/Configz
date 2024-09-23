package zzzank.libs.config.api.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.file.FileFormat;

import java.nio.file.Path;

/**
 * holds multiple config categories
 * @author ZZZank
 */
public interface ConfigRoot extends ConfigCategory {

    /**
     * config name, default to modid, also used as file name
     */
    @Override
    @NotNull String getName();

    @NotNull
    Path getSavePath();

    FileFormat getDefaultFormat();

    void save(@NotNull Path path, @NotNull FileFormat format);

    default void save() {
        save(getSavePath(), getDefaultFormat());
    }

    @Override
    default ConfigCategory getParent() {
        return null;
    }

    @Override
    default boolean isRoot() {
        return true;
    }
}
