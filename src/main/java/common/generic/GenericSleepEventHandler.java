package common.generic;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericSleepEventHandler<T> implements EventHandler<GenericEvent<T>> {

    protected String name;

    public GenericSleepEventHandler() {
    }

    public GenericSleepEventHandler(String name) {
        this.name = name;
    }

    public void onEvent(GenericEvent<T> event, long sequence, boolean endOfBatch) throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("消费者EventHandler(" + name + ")" + ":" + event.get()
                + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }

}
