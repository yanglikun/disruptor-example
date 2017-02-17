package example_basic.handleonce;

import com.lmax.disruptor.dsl.Disruptor;
import common.MainTemplate;
import common.generic.GenericEvent;
import common.generic.GenericEventModHandler;
import example_basic.DependenciesChainMain;

/**
 * Created by yanglikun on 2017/2/17.
 */
public class ModMain extends MainTemplate {

    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        //批量构建一批handler
        int handlerCounts = 10;
        GenericEventModHandler[] handlers = new GenericEventModHandler[handlerCounts];
        for (int i = 0; i < handlerCounts; i++) {
            handlers[i] = new GenericEventModHandler("[handler-" + i + "]", i, handlerCounts);
        }
        disruptor.handleEventsWith(handlers);
    }

    public static void main(String[] args) {
        new ModMain().run();
    }

}
