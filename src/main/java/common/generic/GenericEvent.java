package common.generic;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericEvent<T> {

    private T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
