package com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.strategy02.loser.biz;



import com.xiaou.xiaoueasyprojectbackend.module.support.gof23.behaviour03.strategy02.loser.core.Component;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class RainHandler {

    private final Map<ERainEvent, Function<RainInfo, Boolean>> eventFunction = init();

    /**
     * 模拟红包雨事件 不同的事件触发不同的策略处理器
     */
    private Map<ERainEvent, Function<RainInfo, Boolean>> init() {
        Map<ERainEvent, Function<RainInfo, Boolean>> eventFunctionMap = new ConcurrentHashMap<>();
        eventFunctionMap.put(ERainEvent.INIT, this::doInit);
        eventFunctionMap.put(ERainEvent.START, this::doStart);
        eventFunctionMap.put(ERainEvent.END, this::doEnd);
        return eventFunctionMap;
    }

    private final Queue<RainInfo> rainInfoQueue = new ArrayDeque<>();

    private void work() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    RainInfo rainInfo = rainInfoQueue.poll();
                    if (Objects.nonNull(rainInfo)) {
                        eventFunction.get(rainInfo.getRainEvent()).apply(rainInfo);
                    }
                    Thread.sleep(1000L);
                }
            } catch (Exception ignore) {

            }
        });
        thread.start();
    }


    private Boolean doInit(RainInfo rainInfo) {
        System.out.println("doInit :" + rainInfo);
        rainInfo.setRainEvent(ERainEvent.START);
        rainInfoQueue.add(rainInfo);
        return true;
    }

    private Boolean doStart(RainInfo rainInfo) {
        System.out.println("doStart :" + rainInfo);
        rainInfo.setRainEvent(ERainEvent.END);
        rainInfoQueue.add(rainInfo);
        return true;
    }

    private Boolean doEnd(RainInfo rainInfo) {
        System.out.println("doEnd :" + rainInfo);
        rainInfo.setRainEvent(ERainEvent.INIT);
        rainInfoQueue.add(rainInfo);
        System.out.println();
        return true;
    }

    public void start() {
        RainInfo info = new RainInfo();
        info.setRainEvent(ERainEvent.INIT);
        rainInfoQueue.add(info);
        this.work();
    }

}
