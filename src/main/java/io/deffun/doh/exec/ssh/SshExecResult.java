package io.deffun.doh.exec.ssh;

import io.deffun.doh.exec.ExecResult;

import java.util.List;

record SshExecResult(int statusCode, String content, String error) implements ExecResult {
    @Override
    public boolean isSuccess() {
        return !isFailure();
    }

    @Override
    public boolean isFailure() {
        return statusCode != 0;
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public List<String> contents() {
        return content.lines().toList();
    }

    @Override
    public String error() {
        return error;
    }

    @Override
    public List<String> errors() {
        return error.lines().toList();
    }
}
