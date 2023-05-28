package io.deffun.doh;

import io.deffun.doh.exec.CommandExecutor;
import io.deffun.doh.exec.ExecResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabasePluginTest {
    @Mock
    private CommandExecutor commandExecutor;
    private DatabasePlugin databasePlugin;
    @Mock
    private ExecResult execResult;

    @BeforeEach
    void setUp() {
        databasePlugin = new DatabasePlugin("mysql", commandExecutor);
    }

    @Test
    void mySqlLinkTest() {
        when(commandExecutor.tryExecute(any())).thenReturn(execResult);
        assertDoesNotThrow(() -> databasePlugin.link("mydb", "myapp"));
    }
}
