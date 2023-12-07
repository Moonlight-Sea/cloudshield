package pers.sea.shield.batch.monitor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.stereotype.Service;
import pers.sea.shield.batch.handler.JobHandler;

import java.io.File;

/**
 * Flag 文件监听器
 *
 * @author moon on 2023/11/27
 */
@Service
@Slf4j
public class FileListener extends FileAlterationListenerAdaptor {

    private final JobHandler jobHandle;

    public FileListener(JobHandler jobHandler) {
        this.jobHandle = jobHandler;
    }

    @Override
    public void onFileCreate(File file) {
        log.info("监听到文件创建：{}", file.getAbsolutePath());
        jobHandle.findThenCreate(file);
    }

}
