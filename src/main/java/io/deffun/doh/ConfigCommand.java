package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;
import io.deffun.doh.exec.KeyValueOption;

import java.util.List;

/**
 * See <a href="https://dokku.com/docs/configuration/environment-variables/">documentation</a>.
 */
public final class ConfigCommand {
    private final CommandExecutor commandExecutor;

    ConfigCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void set(String appName, String key, String value) {
        Command command = new Command(DokkuCommands.CONFIG, "set", List.of(appName), List.of(new KeyValueOption(key, value)));
        commandExecutor.tryExecute(command);
    }

    public void set(String appName, List<KeyValueOption> keyValueOptions) {
        Command command = new Command(DokkuCommands.CONFIG, "set", List.of(appName), keyValueOptions);
        commandExecutor.tryExecute(command);
    }

    public String get(String appName, String envVar) {
        Command command = new Command(DokkuCommands.CONFIG, "get", List.of(appName, envVar));
        return commandExecutor.tryExecute(command).content();
    }
}
