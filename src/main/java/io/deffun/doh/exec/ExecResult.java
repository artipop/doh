package io.deffun.doh.exec;

import java.util.List;

public interface ExecResult {
    boolean isSuccess();

    boolean isFailure();

    List<String> contents();

    String content();

    List<String> errors();

    String error();
}
