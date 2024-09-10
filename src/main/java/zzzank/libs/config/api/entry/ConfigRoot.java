package zzzank.libs.config.api.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.save.SaveFormat;

import java.nio.file.Path;

/**
 * @author ZZZank
 */
public interface ConfigRoot extends ConfigCategory {

    @Deprecated
    @Override
    default ConfigCategory getParent() {
        return null;
    }

    @NotNull
    Class<?> getIdentifier();

    @NotNull
    Path getSavePath();

    void save(@NotNull SaveFormat format);

    @Override
    default boolean isRoot() {
        return true;
    }
}
