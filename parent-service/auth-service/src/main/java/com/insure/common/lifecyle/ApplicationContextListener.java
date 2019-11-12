package com.insure.common.lifecyle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * application 上下文 启动之后时间监听
 *
 * @Author: fgd
 * @Date: 2019-11-12
 */
@Slf4j
@Component
public class ApplicationContextListener implements ApplicationListener<ApplicationStartedEvent> {


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("应用上下文已启动--");
    }

}
