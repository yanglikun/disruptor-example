package common.generic;

import com.lmax.disruptor.EventHandler;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericExceptionEventHandler<T> implements EventHandler<GenericEvent<T>> {

    protected String name;

    public GenericExceptionEventHandler() {
    }

    public GenericExceptionEventHandler(String name) {
        this.name = name;
    }

    public void onEvent(GenericEvent<T> event, long sequence, boolean endOfBatch) throws Exception {

        if ("ex".equals(event.get().toString())) {
            throw new RuntimeException("手动异常");
        }
        System.out.println("消费者EventHandler(" + name + ")" + ":消息内容->" + event.get()
                + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }

}
