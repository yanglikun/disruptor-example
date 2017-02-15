package main;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import common.LongEvent;
import common.LongEventFactory;
import common.LongEventHandler;
import common.LongEventProducer;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;


/**
 * 基础执行
 * Created by yanglikun on 2017/2/15.
 */
public class BasicMain {

    public static void main(String[] args) throws Exception {

        // The factory for the event
        //event工厂,用于让disruptor预先分配工厂
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        //指定ring buffer的大小
        int bufferSize = 1024;

        // Construct the Disruptor
        //构建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, Executors
                .defaultThreadFactory
                        ());

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler("Basic"));

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        //获取ring buffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 20; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
        }
        System.out.println("end.....");
    }
}
