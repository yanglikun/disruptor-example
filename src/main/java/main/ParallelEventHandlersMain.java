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
 * Created by yanglikun on 2017/2/15.
 */
public class ParallelEventHandlersMain {

    public static void main(String[] args) {

        // The factory for the event
        //event工厂,用于让disruptor预先分配工厂
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        //指定ring buffer的大小
        int bufferSize = 8;

        // Construct the Disruptor
        //构建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, Executors
                .defaultThreadFactory
                        ());

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler("parallel-1"),new LongEventHandler("parallel-2"),new LongEventHandler("parallel-3"));

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
    }
}
