package ru.omsu.fctk.translator.emitter.emitter;

import org.junit.Before;
import org.junit.Test;
import ru.omsu.fctk.translator.emitter.Command;
import ru.omsu.fctk.translator.emitter.Emitter;
import ru.omsu.fctk.translator.emitter.IFormatter;
import ru.omsu.fctk.translator.emitter.Method;
import ru.omsu.fctk.translator.emitter.MethodOptions;

import java.io.Writer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddCommandTest {
    Emitter emitter;

    Method method_1;
    Method method_2;

    Command nullCommand;
    Command command_1;
    Command command_2;
    @Before
    public void initialize() {
        emitter = new Emitter(mock(Writer.class), mock(IFormatter.class));

        method_1 = new Method("1", "1", new MethodOptions());
        method_2 = new Method("2", "2", new MethodOptions());
        emitter.addMethod(method_1);
        emitter.addMethod(method_2);

        command_1 = new Command("");
        command_2 = new Command("");
    }
    @Test
    public void shouldAddCommandToOpenedMethod() {
        emitter.openMethod(method_1);
        emitter.addCommand(command_1);

        assertTrue(method_1.getCommands().contains(command_1));
    }
    @Test
    public void shouldAddCommandsInOrder() {
        emitter.openMethod(method_1);
        emitter.addCommand(command_1);
        emitter.addCommand(command_2);

        assertEquals(method_1.getCommands().get(0), command_1);
        assertEquals(method_1.getCommands().get(1), command_2);
    }
    @Test
    public void shouldNotAddCommandToOtherMethods() {
        emitter.openMethod(method_1);
        emitter.addCommand(command_1);
        assertFalse(method_2.getCommands().contains(command_1));
    }
    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfNoMethodIsOpened() {
        emitter.addCommand(command_1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnNullCommand() {
        emitter.openMethod(method_1);
        emitter.addCommand(nullCommand);
    }
}
