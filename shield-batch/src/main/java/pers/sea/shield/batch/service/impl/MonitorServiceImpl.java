package pers.sea.shield.batch.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Service;
import pers.sea.shield.batch.common.constant.BatchProperty;
import pers.sea.shield.batch.monitor.FileListener;
import pers.sea.shield.batch.service.IMonitorService;
import pers.sea.shield.common.core.constant.SymbolConstant;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author moon on 2023/11/27
 */
@Service
@Slf4j
public class MonitorServiceImpl implements IMonitorService {

    private final FileAlterationMonitor monitor = new FileAlterationMonitor(BatchProperty.monitorInterval);
    private final FileListener fileListener;

    public MonitorServiceImpl(FileListener fileListener) {
        this.fileListener = fileListener;
    }

    private void init() {
        Arrays.stream(BatchProperty.monitorDir.split(SymbolConstant.COMMA)).forEach(path -> {
            FileAlterationObserver observer = new FileAlterationObserver(path);
            observer.addListener(fileListener);
            monitor.addObserver(observer);
        });
    }

    @Override
    public void start() throws Exception {
        init();
        monitor.start();
        log.info("开始监听: {} 文件变动", BatchProperty.monitorDir);
    }

    @Override
    public void addObserver(String path) {
        boolean isExist = false;
        for (FileAlterationObserver observer : monitor.getObservers()) {
            if (Objects.equals(observer.getDirectory().getName(), path))
                isExist = true;
        }
        if (!isExist) {
            FileAlterationObserver observer = new FileAlterationObserver(path);
            observer.addListener(fileListener);
            monitor.addObserver(observer);
        }
    }

    @Override
    public void stopObserver(String path) {
        for (FileAlterationObserver observer : monitor.getObservers()) {
            if (Objects.equals(observer.getDirectory().getName(), path))
                monitor.removeObserver(observer);
        }
    }
}
