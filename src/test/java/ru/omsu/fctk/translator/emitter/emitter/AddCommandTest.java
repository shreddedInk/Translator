package ru.omsu.fctk.translator.emitter.emitter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.omsu.fctk.translator.emitter.Command;
import ru.omsu.fctk.translator.emitter.Emitter;
import ru.omsu.fctk.translator.emitter.IFormatter;
import ru.omsu.fctk.translator.emitter.Method;
import ru.omsu.fctk.translator.emitter.MethodOptions;

import java.io.Writer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddCommandTest {
    Emitter emitter;

    Method method_1;
    Method method_2;

    Command command_1;
    Command command_2;

    @Before
    public void initialize() {
        emitter = new Emitter(mock(Writer.class), Mockito.mock(IFormatter.class));

        method_1 = new Method("1", "", new MethodOptions());
        method_2 = new Method("2", "", new MethodOptions());
        emitter.addMethod(method_1);
        emitter.addMethod(method_2);

        command_1 = new Command("");
        command_2 = new Command("");
    }

    @Test
    public void givenOpenedMethod_whenAddCommand_thenCommandAdded() {
        emitter.openMethod(method_1);
        emitter.addCommand(command_1);

        assertTrue("command_1 should be added to method_1",
                method_1.getCommands().contains(command_1));
    }

    @Test
    public void givenOpenedMethod_whenAddMultipleCommands_thenCommandsAreInOrder() {
        emitter.openMethod(method_1);
        emitter.addCommand(command_1);
        emitter.addCommand(command_2);

        assertEquals("First command should be command_1", command_1, method_1.getCommands().get(0));
        assertEquals("Second command should be command_2", command_2, method_1.getCommands().get(1));
    }

    @Test
    public void givenOpenedMethod_whenAddCommand_thenOtherMethodsUnchanged() {
        emitter.openMethod(method_1);
        emitter.addCommand(command_1);

        assertFalse("command_1 should not be added to method_2",
                method_2.getCommands().contains(command_1));
    }

    @Test(expected = IllegalStateException.class)
    public void givenNoOpenedMethod_whenAddCommand_thenThrowException() {
        emitter.addCommand(command_1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenOpenedMethod_whenAddNullCommand_thenThrowException() {
        emitter.openMethod(method_1);
        emitter.addCommand(null);
    }
}
