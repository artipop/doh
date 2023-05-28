package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;

import java.util.List;

/**
 * Supports the following plugins:
 * <ul>
 *     <li><a href="https://github.com/dokku/dokku-mysql">MySQL</a></li>
 *     <li><a href="https://github.com/dokku/dokku-mariadb">MariaDB</a></li>
 *     <li><a href="https://github.com/dokku/dokku-postgres">PostgreSQL</a></li>
 * </ul>
 */
public final class DatabasePlugin {
    private final String baseCommand;
    private final CommandExecutor commandExecutor;

    DatabasePlugin(String baseCommand, CommandExecutor commandExecutor) {
        this.baseCommand = baseCommand;
        this.commandExecutor = commandExecutor;
    }

    public void create(String dbName) {
        Command command = new Command(baseCommand, "create", List.of(dbName));
        commandExecutor.tryExecute(command);
    }

    public void link(String dbName, String appName) {
        Command command = new Command(baseCommand, "link", List.of(dbName, appName));
        commandExecutor.tryExecute(command);
    }

    public void destroyForce(String dbName) {
        Command command = new Command(baseCommand, "destroy", List.of(dbName, "--force"));
        commandExecutor.tryExecute(command);
    }
}
