package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;

import java.net.URL;
import java.util.List;

/**
 * See <a href="https://dokku.com/docs/deployment/builders/herokuish-buildpacks/">documentation</a>.
 */
public final class BuildpacksCommand {
    private final CommandExecutor commandExecutor;

    BuildpacksCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public void add(String appName, String url) {
        Command command = new Command(DokkuCommands.BUILDPACKS, "add", List.of(appName, url));
        commandExecutor.tryExecute(command);
    }

    public void add(String appName, URL url) {
        Command command = new Command(DokkuCommands.BUILDPACKS, "add", List.of(appName, url.toExternalForm()));
        commandExecutor.tryExecute(command);
    }
}
