package io.deffun.doh.exec.ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.deffun.doh.ClientInvocationException;
import io.deffun.doh.ClientTimeoutException;
import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;
import io.deffun.doh.exec.ExecResult;
import io.deffun.doh.exec.KeyValueOption;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class JSchCommandExecutor implements CommandExecutor {
    private static final int DEFAULT_SLEEP_TIME_MS = 100;

    private final Session session;
    private final long sleepTimeMs;

    public JSchCommandExecutor(Session session) {
        this(session, DEFAULT_SLEEP_TIME_MS);
    }

    public JSchCommandExecutor(Session session, long sleepTimeMs) {
        this.session = Objects.requireNonNull(session);
        this.sleepTimeMs = sleepTimeMs;
    }

    @Override
    public ExecResult execute(Command command) {
        Objects.requireNonNull(command.name());
        Objects.requireNonNull(command.subcommand());
        StringBuilder commandBuilder = new StringBuilder(command.name())
                .append(":")
                .append(command.subcommand());
        List<String> options = command.options();
        if (options != null) {
            commandBuilder
                    .append(" ")
                    .append(String.join(" ", options));
        }
        List<KeyValueOption> keyValueOptions = command.keyValueOptions();
        if (keyValueOptions != null) {
            commandBuilder
                    .append(" ")
                    .append(keyValueOptions.stream()
                            .map(kv -> "%s=%s".formatted(kv.key(), kv.value()))
                            .collect(Collectors.joining()));
        }
        if (!session.isConnected()) {
            throw new IllegalStateException("Session is not connected.");
        }
        ChannelExec channelExec = null;
        try {
            channelExec = (ChannelExec) session.openChannel("exec");

            ByteArrayOutputStream stdErr = new ByteArrayOutputStream();
            ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
            channelExec.setOutputStream(stdOut, true);
            channelExec.setErrStream(stdErr, true);
            channelExec.setCommand(commandBuilder.toString());
            channelExec.connect();
            awaitClose(channelExec);
            return new SshExecResult(
                    channelExec.getExitStatus(),
                    stdOut.toString(StandardCharsets.UTF_8),
                    stdErr.toString(StandardCharsets.UTF_8)
            );
        } catch (JSchException e) {
            throw new ClientInvocationException(e);
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    private void awaitClose(ChannelExec channelExec) {
        while (!channelExec.isClosed()) {
            try {
                Thread.sleep(sleepTimeMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ClientTimeoutException(e);
            }
        }
    }
}
