#disruptor example
**环境**：JDK version：1.6 disruptor：3.3.6

**disruptor可以简单的看做是内存MQ(内存消息队列)**


![image](https://cloud.githubusercontent.com/assets/1728527/23055993/06f22c7e-f524-11e6-9fb5-5219b146983f.png)

* producer：生产事件
* disruptor（确切的说是里面的ring buffer）：存放事件
* EventHandler：处理事件，进行业务逻辑
* 对于一个事件，所有的EventHandler都能接收到，也就是说一个事件消息会被处理多次(也可以使用wokerpool只让一个消息处理一次，后面会说)

# 代码示例
## 没有顺序，并行执行
step1、step2、step3三个处理器都会接收到消息而且执行没有先后顺序 **测试类：example_basic.ParallelEventHandlersMain**
![image](https://cloud.githubusercontent.com/assets/1728527/23056364/12b53f40-f526-11e6-9fdb-516aa4fbf38d.png)

## 有先后依赖顺序执行
step1、step2、step3 三个步骤会严格按照顺序执行 **测试类example_basic.DependenciesChainMain**
![image](https://cloud.githubusercontent.com/assets/1728527/23056553/edf97a30-f526-11e6-940f-2880977632ff.png)

## 菱形顺序
step1、step2之间没有顺序，但是step3会等到step1、step2都执行完后才会执行 **测试类example_basic.DependenciesDiamondMain**
![image](https://cloud.githubusercontent.com/assets/1728527/23056600/24204d46-f527-11e6-963c-34c2e591d6ef.png)

## 链式依赖顺序
chain1、chain2之间没有依赖关系，but chain1中的 step1-1、step1-2有先后关系、chain12中的 step2-1、step2-2有先后关系。测试类**example_basic.DependenciesMultiChainMain**
![image](https://cloud.githubusercontent.com/assets/1728527/23056717/b9a3cb04-f527-11e6-854d-d042660641fd.png)

## 异常处理
 异常需要设置setDefaultExceptionHandler，要不然处理器抛出异常后，就不会再处理事件了。**测试类example_other.SimulationExceptionMain**
 
## 监控disruptor的负载
 调用ringBuffer.remainingCapacity()查看还有多少没使用，**测试类example_other.MonitorDisruptorMain**
