package io.deffun.doh.exec;

import java.util.List;

public record Command(String name, String subcommand, List<String> options, List<KeyValueOption> keyValueOptions) {
    public Command(String command, String subcommand, List<String> options) {
        this(command, subcommand, options, null);
    }

    public Command(String command, String subcommand) {
        this(command, subcommand, null, null);
    }
}
