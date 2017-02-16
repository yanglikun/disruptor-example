package common.Long;

import com.lmax.disruptor.EventFactory;

/**
 * 提供disruptor预先分配event
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}