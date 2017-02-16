package main;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import common.generic.GenericEvent;
import common.generic.GenericEventFactory;
import common.generic.GenericEventHandler;
import common.generic.GenericEventProducer;

import java.util.concurrent.Executors;

/**
 * 本地持久化，远程持久化，完成后再处理业务逻辑
 * Created by yanglikun on 2017/2/16.
 */
public class DependenciesMain {

    public static void main(String[] args) {

        GenericEventFactory<GenericEvent<String>> eventFactory = new GenericEventFactory<GenericEvent<String>>();
        int bufferSize = 1024;
        Disruptor<GenericEvent<String>> disruptor = new Disruptor(eventFactory, bufferSize,
                Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new GenericEventHandler<String>("本地持久化")
                , new GenericEventHandler<String>("远程持久化"))
                .then(new GenericEventHandler<String>("处理业务逻辑"));

        disruptor.start();

        RingBuffer<GenericEvent<String>> ringBuffer = disruptor.getRingBuffer();
        GenericEventProducer<String> producer = new GenericEventProducer(ringBuffer);
        for (long i = 0; i < 5; i++) {
            producer.onData("下订单【" + i + "】");
        }
    }

}
