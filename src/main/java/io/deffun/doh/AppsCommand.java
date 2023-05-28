package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;

import java.util.List;

/**
 * See <a href="https://dokku.com/docs/deployment/application-management/">documentation</a>.
 */
public final class AppsCommand {
    private final CommandExecutor commandExecutor;

    AppsCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void create(String appName) {
        Command command = new Command(DokkuCommands.APPS, "create", List.of(appName));
        commandExecutor.tryExecute(command);
    }

    public List<String> list() {
        Command command = new Command(DokkuCommands.APPS, "list");
        return commandExecutor.tryExecute(command).contents();
    }

    public void destroyForce(String appName) {
        Command command = new Command(DokkuCommands.APPS, "destroy", List.of(appName, "--force"));
        commandExecutor.tryExecute(command);
    }
}
