package io.deffun.doh.exec.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.deffun.doh.ClientInvocationException;
import io.deffun.doh.Dokku;

import java.util.function.Consumer;

public final class JSchDokkuExecutor implements SshDokkuExecutor {
    private final JSch jSch;
    private final String dokkuUser;
    private final String dokkuHost;

    public JSchDokkuExecutor(JSch jSch, String dokkuUser, String dokkuHost) {
        this.jSch = jSch;
        this.dokkuUser = dokkuUser;
        this.dokkuHost = dokkuHost;
    }

    @Override
    public void session(Consumer<Dokku> dokkuConsumer) {
        Session session = null;
        try {
            session = jSch.getSession(dokkuUser, dokkuHost);
            session.connect();
            Dokku dokku = new Dokku(new JSchCommandExecutor(session));
            dokkuConsumer.accept(dokku);
        } catch (JSchException e) {
            throw new ClientInvocationException(e);
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
