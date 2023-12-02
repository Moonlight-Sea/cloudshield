package pers.sea.shield.batch.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * IP工具类
 *
 * @author moon on 2023/11/29
 */
@Slf4j
public class IPUtils {


    public static String getIp() {
        String ip = null;
        try {
            ip = java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.error("获取IP地址异常", e);
        }
        return ip;
    }

}
