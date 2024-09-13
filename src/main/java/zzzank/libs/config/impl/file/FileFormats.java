package zzzank.libs.config.impl.file;

import lombok.AllArgsConstructor;
import lombok.val;
import zzzank.libs.config.api.file.FileFormat;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class FileFormats {

    private static final Map<String, EnumLike<FileFormat>> REGISTERED = new LinkedHashMap<>();
    public static final EnumLike<TomlFormat> TOML = register(TomlFormat.class, "toml");
    public static final EnumLike<JsonFormat> JSON = register(JsonFormat.class, "json");
    public static final EnumLike<CfgFormat> CFG = register(CfgFormat.class, "cfg");

    public static <T extends FileFormat> EnumLike<T> register(Class<T> type, String name) {
        if (REGISTERED.containsKey(name)) {
            //todo: more info
            throw new IllegalArgumentException("already registered");
        }
        val enumLike = new EnumLike<>(type, name, REGISTERED.size());
        REGISTERED.put(enumLike.name, (EnumLike<FileFormat>) enumLike);
        return enumLike;
    }

    @AllArgsConstructor
    public static class EnumLike<T> {
        public final Class<T> type;
        public final String name;
        public final int ordinal;
    }
}
