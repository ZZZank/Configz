package zzzank.libs.config.utils;

/**
 * @author ZZZank
 */
public interface Toolkit {

    static <T> T tryOr(TryAction<T> action, T defaultValue) {
        try {
            return action.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    interface TryAction<T> {
        T get() throws Exception;
    }
}
