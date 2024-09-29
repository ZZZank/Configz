package zzzank.libs.config.utils;

/**
 * @author ZZZank
 */
public interface Toolkit {

    static void asserT(boolean success, String failMessage) {
        if (!success) {
            throw new AssertionError(failMessage);
        }
    }

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
