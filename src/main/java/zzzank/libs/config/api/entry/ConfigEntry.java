package zzzank.libs.config.api.entry;

import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author ZZZank
 */
public interface ConfigEntry<T> extends Supplier<T> {

    @NotNull
    String getName();

    @NotNull
    @Override
    T get();

    void set(@NotNull T newValue);

    @NotNull
    ConfigAttribute getAttribute();

    @NotNull
    ConfigBound<T> getBound();

    ConfigCategory getParent();

    /**
     * cast a config entry to a config entry with desired return value type
     * <p>
     * WARNING: there's no fail-safe in this method, handle {@link ClassCastException} yourself
     * <p>
     * use {@link ConfigEntry#asEntrySafe(Class)} if you want to ensure safe usage
     *
     * @param type the desired type of the config entry
     * @return cast-ed config entry
     * @see ConfigEntry#asEntrySafe(Class)
     */
    default <C> ConfigEntry<C> asEntryFor(Class<C> type) {
        return (ConfigEntry<C>) this;
    }

    /**
     * a safe version of {@link ConfigEntry#asEntryFor(Class)}, where config value will be accessed once
     * before return, and any exceptions occurred during this process will be caught
     *
     * @return an {@link Optional} holding returned config entry, or an empty {@link Optional} if any
     * exception occurred.
     */
    default <C> Optional<ConfigEntry<C>> asEntrySafe(Class<C> type) {
        try {
            var entry = asEntryFor(type);
            entry.get(); //access once to catch possible ClassCastException
            return Optional.of(entry);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    default boolean isCategory() {
        return false;
    }

    default ConfigCategory asCategory() {
        if (isCategory()) {
            return ((ConfigCategory) this);
        }
        throw new IllegalStateException("Not a ConfigCategory");
    }

    default boolean isRoot() {
        return false;
    }

    default ConfigRoot asRoot() {
        if (isRoot()) {
            return ((ConfigRoot) this);
        }
        throw new IllegalStateException("Not a ConfigRoot");
    }
}
