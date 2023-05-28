package io.deffun.doh;

/**
 * Currently supported Dokku commands.
 * It is just a handy class with constants.
 */
public final class DokkuCommands {
    static final String APPS = "apps";
    static final String CONFIG = "config";
    static final String SSH_KEYS = "ssh-keys";
    static final String BUILDER = "builder";
    static final String BUILDPACKS = "buildpacks";
    static final String REGISTRY = "registry";
    static final String LETSENCRYPT = "letsencrypt";

    private DokkuCommands() {
    }
}
