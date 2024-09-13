package zzzank.libs.config.impl.entry.listener;

import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.entry.ConfigListener;

/**
 * @author ZZZank
 */
public class AutoSaveListener<T> implements ConfigListener<T> {

    public static final AutoSaveListener<Object> INSTANCE = new AutoSaveListener<>();

    public static <T> AutoSaveListener<T> of() {
        return (AutoSaveListener<T>) INSTANCE;
    }

    private AutoSaveListener() {}

    @Override
    public void postSet(ConfigEntry<T> entry, T oldValue, T newValue) {
        entry.getRoot().save();
    }
}
