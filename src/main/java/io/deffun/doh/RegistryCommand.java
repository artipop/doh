package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;

import java.util.List;

/**
 * See <a href="https://dokku.com/docs/advanced-usage/registry-management/">documentation</a>.
 */
public final class RegistryCommand {
    private final CommandExecutor commandExecutor;

    RegistryCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void set(String appName, String imagesRepoName, String imageName) {
        Command command = new Command(DokkuCommands.REGISTRY, "set", List.of(appName, imagesRepoName, imageName));
        commandExecutor.tryExecute(command);
    }
}
