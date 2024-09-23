package zzzank.libs.config.natived.annotation;

import zzzank.libs.config.api.entry.ConfigEntry;
import zzzank.libs.config.api.entry.ConfigRoot;
import zzzank.libs.config.impl.file.FileFormats;

/**
 * @author ZZZank
 */
public @interface RootConfig {

    /**
     * the unique id of this config
     */
    String id();

    /**
     * default category value, used when a category does not specify category name
     */
    String defaultCategory();

    /**
     * @see FileFormats.EnumLike#name()
     */
    String format() default "cfg";

    /**
     * whether {@link ConfigRoot#save()} should be called when there's new change to a config entry, aka
     * when {@link ConfigEntry#set(Object)} is called and config value does change
     */
    boolean autoSave() default false;

    /**
     * whether
     */
    boolean syncWithField() default true;
}
