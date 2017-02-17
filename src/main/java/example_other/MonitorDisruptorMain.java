package example_other;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import common.MainTemplate;
import common.generic.GenericEvent;
import common.generic.GenericSleepEventHandler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用ringBuffer.remainingCapacity()监控负载(环上还有多少个slot可用)
 * <p>
 * Created by yanglikun on 2017/2/17.
 */
public class MonitorDisruptorMain extends MainTemplate {

    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericSleepEventHandler<String>("handler-1"));
    }

    @Override
    protected void doAfterDisruptorStart(final RingBuffer<GenericEvent<String>> ringBuffer) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("剩余坑位:" + ringBuffer.remainingCapacity());
            }
        }, 1000, 2000);
    }

    public static void main(String[] args) {
        new MonitorDisruptorMain().run();
    }
}
