package example_basic;

import com.lmax.disruptor.dsl.Disruptor;
import common.MainTemplate;
import common.generic.GenericEvent;
import common.generic.GenericEventHandler;

/**
 * 链式
 * step1、step2、step3会按照顺序处理
 * <p>
 * Created by yanglikun on 2017/2/16.
 */
public class DependenciesChainMain extends MainTemplate {

    public void addHandler(Disruptor<GenericEvent<String>> disruptor) {
        disruptor.handleEventsWith(new GenericEventHandler<String>("step1"))
                .then(new GenericEventHandler<String>("step2"))
                .then(new GenericEventHandler<String>("step3"));
    }

    public static void main(String[] args) {
        new DependenciesChainMain().run();
    }
}
