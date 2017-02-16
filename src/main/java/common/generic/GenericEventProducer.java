package common.generic;

import com.lmax.disruptor.RingBuffer;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class GenericEventProducer<T> {

    private final RingBuffer<GenericEvent<T>> ringBuffer;

    public GenericEventProducer(RingBuffer<GenericEvent<T>> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(T data) {
        long sequence = ringBuffer.next();  // Grab the next sequence 获取下一个sequence
        try {
            GenericEvent<T> event = ringBuffer.get(sequence); // Get the entry in the Disruptor 获取entry对象
            // for the sequence 对应sequence位置上的event
            event.set(data);  // Fill with data 填充业务数据
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
