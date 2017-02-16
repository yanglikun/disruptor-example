package example_basic;

import com.lmax.disruptor.dsl.Disruptor;
import common.MainTemplate;
import common.generic.GenericEvent;
import common.generic.GenericEventHandler;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class WorkerPoolMain extends MainTemplate {

    public void addHandleEvents(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericEventHandler<String>("step1"))
                .then(new GenericEventHandler<String>("step2"))
                .then(new GenericEventHandler<String>("step3"));
        disruptor.handleEventsWithWorkerPool();
    }

    public static void main(String[] args) {
        new WorkerPoolMain().run();
    }
}
