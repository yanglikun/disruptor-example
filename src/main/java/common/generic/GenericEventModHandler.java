package common.generic;

import com.lmax.disruptor.EventHandler;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericEventModHandler<T> implements EventHandler<GenericEvent<T>> {

    private final long ordinal;

    private final long numberOfConsumers;

    private String name;

    public GenericEventModHandler(String name, long ordinal, long numberOfConsumers) {
        this.ordinal = ordinal;
        this.numberOfConsumers = numberOfConsumers;
        this.name = name;
    }

    public void onEvent(GenericEvent<T> event, long sequence, boolean endOfBatch) throws Exception {
        if ((sequence % numberOfConsumers) != ordinal) {
            //不应该自己处理
            return;
        }

        System.out.println("消费者EventModHandler(" + this.name + ")" + ":" + event.get()+":sequence:"+sequence
                + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }


}
