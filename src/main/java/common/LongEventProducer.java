package common;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 发布事件
 * 和LongEventProducerWithTranslator做的是一件事
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();  // Grab the next sequence 获取下一个sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor 获取entry对象
            // for the sequence 对应sequence位置上的event
            event.set(bb.getLong(0));  // Fill with data 填充业务数据
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}