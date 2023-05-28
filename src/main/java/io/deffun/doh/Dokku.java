package io.deffun.doh;

import io.deffun.doh.exec.CommandExecutor;

public final class Dokku {
    private final CommandExecutor commandExecutor;

    public Dokku(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public AppsCommand apps() {
        return new AppsCommand(commandExecutor);
    }

    public ConfigCommand config() {
        return new ConfigCommand(commandExecutor);
    }

    public SshKeysCommand sshKeys() {
        return new SshKeysCommand(commandExecutor);
    }

    public BuilderCommand builder() {
        return new BuilderCommand(commandExecutor);
    }

    public BuildpacksCommand buildpacks() {
        return new BuildpacksCommand(commandExecutor);
    }

    public RegistryCommand registry() {
        return new RegistryCommand(commandExecutor);
    }

    public LetsEncryptPlugin letsEncryptPlugin() {
        return new LetsEncryptPlugin(commandExecutor);
    }

    public DatabasePlugin mariaDbPlugin() {
        return new DatabasePlugin("mariadb", commandExecutor);
    }

    public DatabasePlugin mySqlPlugin() {
        return new DatabasePlugin("mysql", commandExecutor);
    }

    public DatabasePlugin postgresPlugin() {
        return new DatabasePlugin("postgres", commandExecutor);
    }

    public DatabasePlugin mongoPlugin() {
        return new DatabasePlugin("mongo", commandExecutor);
    }
}
