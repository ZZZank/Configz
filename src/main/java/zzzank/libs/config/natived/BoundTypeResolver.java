package zzzank.libs.config.natived;

import lombok.val;
import zzzank.libs.config.api.bound.ConfigBound;
import zzzank.libs.config.impl.bound.complex.array.*;
import zzzank.libs.config.impl.bound.obj.StrBound;
import zzzank.libs.config.impl.bound.primitive.BoolBound;
import zzzank.libs.config.impl.bound.primitive.CharBound;
import zzzank.libs.config.impl.bound.primitive.number.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZZank
 */
public class BoundTypeResolver {

    private static final Map<Class<?>, BoundConstructor> ADAPTERS = new HashMap<>();

    static {
        //int
        register(int.class, IntBound::new);
        register(int[].class, IntArrayBound::new);
        register(Integer.class, IntBound::new);
        register(Integer[].class, arrayCtor(IntBound.DEFAULT_ZERO));
        register(byte.class, ByteBound::new);
        register(byte[].class, ByteArrayBound::new);
        register(Byte.class, ByteBound::new);
        register(Byte[].class, arrayCtor(ByteBound.DEFAULT_ZERO));
        register(short.class, ShortBound::new);
        register(short[].class, ShortArrayBound::new);
        register(Short.class, ShortBound::new);
        register(Short[].class, arrayCtor(ShortBound.DEFAULT_ZERO));
        register(long.class, LongBound::new);
        register(long[].class, LongArrayBound::new);
        register(Long.class, LongBound::new);
        register(Long[].class, arrayCtor(LongBound.DEFAULT_ZERO));
        //float
        register(float.class, FloatBound::new);
        register(float[].class, FloatArrayBound::new);
        register(Float.class, FloatBound::new);
        register(Float[].class, arrayCtor(FloatBound.DEFAULT_ZERO));
        register(double.class,      DoubleBound::new);
        register(double[].class,    DoubleArrayBound::new);
        register(Double.class,      DoubleBound::new);
        register(Double[].class,    arrayCtor(DoubleBound.DEFAULT_ZERO));
        //other primitive
        register(boolean.class, BoolBound::new);
        register(boolean[].class, BoolArrayBound::new);
        register(Boolean.class, BoolBound::new);
        register(Boolean[].class, arrayCtor(BoolBound.DEFAULT_FALSE));
        register(char.class, CharBound::new);
        register(char[].class, CharArrayBound::new);
        register(Character.class, CharBound::new);
        register(Character[].class, arrayCtor(CharBound.DEFAULT_MAX));
        //other predefined
        register(String.class, StrBound::new);
        register(String[].class, arrayCtor(StrBound.DEFAULT_EMPTY));
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

    private static <T> BoundConstructor<T[]> arrayCtor(ConfigBound<T> memberBound) {
        return defaultValue -> new ArrayBound<>(defaultValue, memberBound, true);
    }

    public interface BoundConstructor<T> {
        ConfigBound<T> construct(T defaultValue);
    }
}
