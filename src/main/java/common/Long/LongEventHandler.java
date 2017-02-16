package common.Long;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

public class LongEventHandler implements EventHandler<LongEvent> {

    private String handlerName;

    public LongEventHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("消费者Event(" + handlerName + "):" + Thread.currentThread().getName() + " " + event.hashCode
                () + ":" + event
                .getValue());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}