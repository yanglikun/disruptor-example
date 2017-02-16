package common.generic;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by yanglikun on 2017/2/16.
 */
public class DisruptorHolder<T> {

    private int bufferSize = 1024;

    private GenericEventFactory<T> eventFactory = new GenericEventFactory<T>();

    private ThreadFactory threadFactory = Executors.defaultThreadFactory();



}
