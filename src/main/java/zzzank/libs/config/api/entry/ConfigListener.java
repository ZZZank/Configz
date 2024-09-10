package zzzank.libs.config.api.entry;

/**
 * @author ZZZank
 */
public interface ConfigListener<T> {

    void onValueSet(ConfigEntry<T> entry, T oldValue);
}
