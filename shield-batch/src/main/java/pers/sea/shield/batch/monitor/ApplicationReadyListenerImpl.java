package pers.sea.shield.batch.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pers.sea.shield.batch.service.IMonitorService;

/**
 * springboot准备完成时执行
 *
 * @author moon on 2023/11/27
 */
@Component
@Slf4j
public class ApplicationReadyListenerImpl implements ApplicationListener<ApplicationReadyEvent> {

    private final IMonitorService monitorService;

    public ApplicationReadyListenerImpl(IMonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("{} 秒内，springboot准备完成()", event.getTimeTaken().getSeconds());
        try {
            monitorService.start();
        } catch (Exception e) {
            log.error("monitor监听初始化启动失败", e);
        }
    }
}
