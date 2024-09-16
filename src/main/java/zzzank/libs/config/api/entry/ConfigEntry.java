package zzzank.libs.config.api.entry;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.natived.BoundTypeResolver;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author ZZZank
 */
public interface ConfigEntry<T> extends Supplier<T> {

    static String validateName(String name) {
        if (name == null || name.isBlank() || name.contains(".")) {
            throw new IllegalArgumentException("invalid config entry name");
        }
        return name;
    }

    @NotNull
    String getName();

    @NotNull
    @Override
    T get();

    /**
     * similar to {@link ConfigEntry#get()}, but return value will be adapted to desired type
     *
     * @throws IllegalArgumentException if there's no config bound registered for {@code type}
     * @throws IllegalStateException    if registered config bound fails to adapt config value in this config entry
     * @see BoundTypeResolver
     */
    default <T2> T2 getAs(Class<T2> type) {
        val raw = get();
        val fn = BoundTypeResolver.resolve(type);
        if (fn == null) {
            throw new IllegalArgumentException("no config bound registered for type: '%s'".formatted(type));
        }
        val parsed = fn.apply(null).adapt(raw);
        if (parsed == null) { //config bound should never return `null` unless it's default value
            throw new IllegalStateException("unable to complete value adapting");
        }
        return parsed;
    }

    void set(@NotNull T newValue);

    @NotNull
    ConfigAttribute getAttribute();

    List<ConfigListener<T>> getListeners();

    @NotNull
    ConfigBound<T> getBound();

    ConfigCategory getParent();

    @NotNull
    default ConfigRoot getRoot() {
        return this instanceof ConfigRoot root ? root : getParent().getRoot();
    }

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
            val entry = asEntryFor(type);
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
