package zzzank.libs.config.api.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.save.SaveFormat;

import java.nio.file.Path;

/**
 * @author ZZZank
 */
public interface ConfigRoot extends ConfigCategory {

    @NotNull
    Class<?> getIdentifier();

    @NotNull
    Path getSavePath();

    SaveFormat getDefaultFormat();

    void save(@NotNull Path path, @NotNull SaveFormat format);

    default void save() {
        save(getSavePath(), getDefaultFormat());
    }

    @Override
    default ConfigCategory getParent() {
        return null;
    }

    @Override
    @NotNull
    default ConfigRoot getRoot() {
        return this;
    }

    @Override
    default boolean isRoot() {
        return true;
    }
}
