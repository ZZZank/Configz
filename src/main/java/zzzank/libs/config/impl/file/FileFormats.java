package zzzank.libs.config.impl.file;

import lombok.val;
import zzzank.libs.config.api.file.FileFormat;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class FileFormats {

    private static final Map<String, EnumLike<FileFormat>> REGISTERED = new LinkedHashMap<>();
    public static final EnumLike<TomlFormat> TOML = register(new TomlFormat(), "toml");
    public static final EnumLike<JsonFormat> JSON = register(new JsonFormat(), "json");
    public static final EnumLike<CfgFormat> CFG = register(new CfgFormat(), "cfg");

    public static <T extends FileFormat> EnumLike<T> register(T type, String name) {
        if (REGISTERED.containsKey(name)) {
            //todo: more info
            throw new IllegalArgumentException("already registered");
        }
        val enumLike = new EnumLike<>(type, name, REGISTERED.size());
        REGISTERED.put(enumLike.name, (EnumLike<FileFormat>) enumLike);
        return enumLike;
    }

    public static FileFormat get(String name) {
        return REGISTERED.get(name).value;
    }

    public record EnumLike<T>(T value, String name, int ordinal) {
    }
}
