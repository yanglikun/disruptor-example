package example_basic;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * Created by yanglikun on 2017/8/10.
 */
public class BasicMainJava8 {
    public static void main(String[] args) {

        //only one disruptor in application
        Disruptor<StringEvent> disruptor = new Disruptor<StringEvent>(
                StringEvent::new, 2,
                Executors.newFixedThreadPool(5),
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        disruptor.handleEventsWith(
                ((event, sequence, endOfBatch) -> {
                    System.out.println("handler Event:" + event.value);
                })
        );
        disruptor.start();

        disruptor.getRingBuffer()
                .publishEvent((event, sequence, arg) -> event.value = arg, "消息1");

        System.out.println("main end...");

    }

    static class StringEvent {
        public String value;
    }
}
