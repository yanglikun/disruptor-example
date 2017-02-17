package example_basic;

import com.lmax.disruptor.dsl.Disruptor;
import common.MainTemplate;
import common.generic.GenericEvent;
import common.generic.GenericEventHandler;

/**
 * step1、step2、step3 之间 并行处理没有先后顺序
 * Created by yanglikun on 2017/2/15.
 */
public class ParallelEventHandlersMain extends MainTemplate {

    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericEventHandler<String>("step1"),
                new GenericEventHandler<String>("step2")
                , new GenericEventHandler<String>("step3"));
    }

    public static void main(String[] args) {
        new ParallelEventHandlersMain().run();
    }


}
