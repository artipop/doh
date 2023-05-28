package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;

import java.util.List;

/**
 * See <a href="https://dokku.com/docs/deployment/builders/builder-management/">documentation</a>.
 */
public final class BuilderCommand {
    private final CommandExecutor commandExecutor;

    BuilderCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void setSelected(String appName, String builderName) {
        Command command = new Command(DokkuCommands.BUILDER, "set", List.of(appName, "selected", builderName));
        commandExecutor.tryExecute(command);
    }
}
