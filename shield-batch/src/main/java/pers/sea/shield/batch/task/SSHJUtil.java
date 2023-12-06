package pers.sea.shield.batch.task;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.IOException;

/**
 * @author moon on 12/6/2023
 */
public class SSHJUtil {

    /**
     * 设置sshj
     *
     * @param remoteHost 远程服务器地址
     * @param username 用户名
     * @param password 服务器密码
     * @return sshj
     * @throws IOException IO异常
     */
    public static SSHClient setupSshj(String remoteHost, String username, String password) throws IOException {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect(remoteHost);
        client.useCompression();
        client.authPassword(username, password);
        return client;
    }

}
