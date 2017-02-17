package common.generic;

import com.lmax.disruptor.EventHandler;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericEventHandler<T> implements EventHandler<GenericEvent<T>> {

    protected String name;

    public GenericEventHandler() {
    }

    public GenericEventHandler(String name) {
        this.name = name;
    }

    public void onEvent(GenericEvent<T> event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者EventHandler(" + name + ")" + ":" + event.get()
                + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }

}
