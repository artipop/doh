package io.deffun.doh.exec;

import io.deffun.doh.ClientInvocationException;

public interface CommandExecutor {
    ExecResult execute(Command command);

    default ExecResult tryExecute(Command command) {
        return executeExceptionally(command);
    }

    private ExecResult executeExceptionally(Command command) {
        ExecResult execResult = execute(command);
        if (execResult.isFailure()) {
            throw new ClientInvocationException(execResult.error());
        }
        return execResult;
    }
}
