package zzzank.libs.config.api.entry;

import org.jetbrains.annotations.NotNull;

/**
 * @author ZZZank
 */
public interface ConfigListener<T> {

    /**
     * return a new value if you want to change what this "get" action will return
     */
    @NotNull
    default T preGet(ConfigEntry<T> entry, T valueToBeReturn) {
        return valueToBeReturn;
    }

    default void postSet(ConfigEntry<T> entry, T oldValue) {
    }
}
