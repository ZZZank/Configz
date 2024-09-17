package zzzank.libs.config.natived;

import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.impl.bound.primitive.BoolBound;
import zzzank.libs.config.impl.bound.primitive.number.IntBound;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class BoundTypeResolver {

    private static final Map<Class<?>, BoundConstructor> ADAPTERS = new HashMap<>();

    static {
        register(boolean.class, BoolBound::new);
        register(Boolean.class, BoolBound::new);
        register(int.class, IntBound::new);
        register(Integer.class, IntBound::new);
        //todo: look
        /*
        register(boolean.class,     TypeAdapters.bool);
        register(boolean[].class,   TypeAdapters.boolA);
        register(Boolean.class,     TypeAdapters.Bool);
        register(Boolean[].class,   TypeAdapters.BoolA);
        register(float.class,       TypeAdapters.flt);
        register(float[].class,     TypeAdapters.fltA);
        register(Float.class,       TypeAdapters.Flt);
        register(Float[].class,     TypeAdapters.FltA);
        register(double.class,      TypeAdapters.dbl);
        register(double[].class,    TypeAdapters.dblA);
        register(Double.class,      TypeAdapters.Dbl);
        register(Double[].class,    TypeAdapters.DblA);
        register(byte.class,        TypeAdapters.byt);
        register(byte[].class,      TypeAdapters.bytA);
        register(Byte.class,        TypeAdapters.Byt);
        register(Byte[].class,      TypeAdapters.BytA);
        register(char.class,        TypeAdapters.chr);
        register(char[].class,      TypeAdapters.chrA);
        register(Character.class,   TypeAdapters.Chr);
        register(Character[].class, TypeAdapters.ChrA);
        register(short.class,       TypeAdapters.shrt);
        register(short[].class,     TypeAdapters.shrtA);
        register(Short.class,       TypeAdapters.Shrt);
        register(Short[].class,     TypeAdapters.ShrtA);
        register(int.class,         TypeAdapters.int_);
        register(int[].class,       TypeAdapters.intA);
        register(Integer.class,     TypeAdapters.Int);
        register(Integer[].class,   TypeAdapters.IntA);
        register(String.class,      TypeAdapters.Str);
        register(String[].class,    TypeAdapters.StrA);
         */
    }

    public static <T> void register(Class<T> type, BoundConstructor<T> boundConstructor) {
        if (ADAPTERS.containsKey(type)) {
            throw new IllegalArgumentException("already registered");
        }
        ADAPTERS.put(type, boundConstructor);
    }

    public static <T> BoundConstructor<T> resolve(Class<T> type) {
        return ADAPTERS.get(type);
    }

    public static boolean hasAdapterFor(Class<?> type) {
        return ADAPTERS.containsKey(type)
            || Map.class.isAssignableFrom(type)
            || Enum.class.isAssignableFrom(type);
    }

    public interface BoundConstructor<T> {
        ConfigBound<T> construct(T defaultValue);
    }
}
