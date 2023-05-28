package io.deffun.doh.exec.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.OpenSSHConfig;
import io.deffun.doh.ClientConfigurationException;
import io.deffun.doh.Dokku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public interface SshDokkuExecutor {
    void session(Consumer<Dokku> dokkuConsumer);

    static SshDokkuExecutor defaultExecutor(
            Path configFile, Path knownHostsFile,
            Path keyFile, String passphrase,
            String dokkuUser, String dokkuHost
    ) {
        JSch jSch = new JSch();
        if (Files.exists(configFile)) {
            try {
                OpenSSHConfig openSSHConfig = OpenSSHConfig.parseFile(configFile.toFile().getAbsolutePath());
                jSch.setConfigRepository(openSSHConfig);
            } catch (IOException e) {
                throw new ClientConfigurationException(e);
            }
        }
        if (Files.exists(knownHostsFile)) {
            try {
                jSch.setKnownHosts(knownHostsFile.toFile().getAbsolutePath());
            } catch (JSchException e) {
                throw new ClientConfigurationException(e);
            }
        }
        if (Files.exists(keyFile)) {
            try {
                jSch.addIdentity(keyFile.toFile().getAbsolutePath(), passphrase);
            } catch (JSchException e) {
                throw new ClientConfigurationException(e);
            }
        } else {
            throw new ClientConfigurationException("The key file and its passphrase are not set.");
        }
        return new JSchDokkuExecutor(jSch, dokkuUser, dokkuHost);
    }
}
