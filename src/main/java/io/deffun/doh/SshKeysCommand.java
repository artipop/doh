package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;

import java.util.List;

/**
 * See <a href="https://dokku.com/docs/deployment/user-management/">documentation</a>.
 */
public final class SshKeysCommand {
    private final CommandExecutor commandExecutor;

    SshKeysCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void add(String user, String sshKey) {
        Command command = new Command(DokkuCommands.SSH_KEYS, "add", List.of(user, sshKey));
        commandExecutor.tryExecute(command);
    }
}
