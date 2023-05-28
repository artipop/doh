package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;

import java.util.List;

/**
 * See <a href="https://github.com/dokku/dokku-letsencrypt">plugin documentation</a>.
 */
public final class LetsEncryptPlugin {
    private final CommandExecutor commandExecutor;

    LetsEncryptPlugin(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void setEmail(String appName, String email) {
        Command command = new Command(DokkuCommands.LETSENCRYPT, "set", List.of(appName, "email", email));
        commandExecutor.tryExecute(command);
    }

    public void enable(String appName) {
        Command command = new Command(DokkuCommands.LETSENCRYPT, "enable", List.of(appName));
        commandExecutor.tryExecute(command);
    }
}
