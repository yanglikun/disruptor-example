package main;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import common.generic.GenericEvent;
import common.generic.GenericEventFactory;
import common.generic.GenericEventHandler;
import common.generic.GenericEventProducer;

import java.util.concurrent.Executors;

/**
 * step1-1、step1-2是并行执行的<br/>
 * step1-1和step1-2都执行完之后step2才会执行
 * Created by yanglikun on 2017/2/16.
 */
public class DependenciesMain {

    public static void main(String[] args) {

        GenericEventFactory<GenericEvent<String>> eventFactory = new GenericEventFactory<GenericEvent<String>>();
        int bufferSize = 1024;
        Disruptor<GenericEvent<String>> disruptor = new Disruptor(eventFactory, bufferSize,
                Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new GenericEventHandler<String>("step1-1")
                , new GenericEventHandler<String>("step1-2"))
                .then(new GenericEventHandler<String>("step2"));

        disruptor.start();

        RingBuffer<GenericEvent<String>> ringBuffer = disruptor.getRingBuffer();
        GenericEventProducer<String> producer = new GenericEventProducer(ringBuffer);
        for (long i = 0; i < 5; i++) {
            producer.onData("消息【" + i + "】");
        }
    }

}
