package pers.sea.shield.batch.service;

/**
 * 目录监控服务
 *
 * @author moon on 2023/11/27
 */
public interface IMonitorService {

    void start() throws Exception;

    void addObserver(String path);

    void stopObserver(String path);
}
