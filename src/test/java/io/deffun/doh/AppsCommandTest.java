package io.deffun.doh;

import io.deffun.doh.exec.Command;
import io.deffun.doh.exec.CommandExecutor;
import io.deffun.doh.exec.ExecResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppsCommandTest {
    @Mock
    private CommandExecutor commandExecutor;
    private AppsCommand appsCommand;

    @Captor
    private ArgumentCaptor<Command> commandCaptor;
    @Mock
    private ExecResult execResult;

    @BeforeEach
    void setUp() {
        appsCommand = new AppsCommand(commandExecutor);
    }

    @Test
    void appsCreateTest() {
        when(commandExecutor.tryExecute(any())).thenReturn(execResult);
        appsCommand.create("myapp");
        verify(commandExecutor).tryExecute(commandCaptor.capture());
        Command command = commandCaptor.getValue();
        assertEquals("apps", command.name());
        assertEquals("create", command.subcommand());
        assertEquals(1, command.options().size());
        assertEquals("myapp", command.options().get(0));
    }
}
