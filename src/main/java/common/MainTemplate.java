package common;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import common.generic.GenericEvent;
import common.generic.GenericEventFactory;
import common.generic.GenericEventProducer;

import java.util.Scanner;
import java.util.concurrent.Executors;

/**
 * 提供公用的模板，方便测试。
 * 在控制台输入消息内容回车,输入exit退出程序
 * Created by yanglikun on 2017/2/16.
 */
public abstract class MainTemplate {


    public void run() {
        GenericEventFactory<GenericEvent<String>> eventFactory = new GenericEventFactory<GenericEvent<String>>();
        int bufferSize = 1024;
        Disruptor<GenericEvent<String>> disruptor = new Disruptor(eventFactory, bufferSize,
                Executors.defaultThreadFactory());


        addHandler(disruptor);
        disruptor.start();

        RingBuffer<GenericEvent<String>> ringBuffer = disruptor.getRingBuffer();
        GenericEventProducer<String> producer = new GenericEventProducer(ringBuffer);
        for (; ; ) {
            Scanner scan = new Scanner(System.in);
            String msg = scan.nextLine();
            if ("exit".equals(msg)) {
                System.exit(0);
            }
            producer.onData(msg);
        }
    }

    public abstract void addHandler(Disruptor<GenericEvent<String>> disruptor);
}
