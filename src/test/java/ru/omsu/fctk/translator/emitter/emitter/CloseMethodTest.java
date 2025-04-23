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

public class CloseMethodTest {
    Emitter emitter;

    Method method_1;
    Method method_2;
    @Before
    public void initialize() {
        emitter = new Emitter(mock(Writer.class), Mockito.mock(IFormatter.class));

        method_1 = new Method("1", "", new MethodOptions());
        method_2 = new Method("2", "", new MethodOptions());
        emitter.addMethod(method_1);
        emitter.addMethod(method_2);
    }
    @Test(expected = IllegalStateException.class)
    public void shouldCloseOpenedMethod() {
        emitter.openMethod(method_1);
        emitter.closeMethod();

        Command cmd = new Command("");
        emitter.addCommand(cmd);
        assertTrue(method_1.getCommands().contains(cmd));
        assertFalse(method_2.getCommands().contains(cmd));
    }
    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfNoMethodIsOpened() {
        emitter.closeMethod();
    }
    @Test()
    public void shouldLetOpenMethodAfterClosing() {
        emitter.openMethod(method_1);
        emitter.closeMethod();
        emitter.openMethod(method_2);
        emitter.addCommand(mock(Command.class));

        Command cmd = new Command("");
        emitter.addCommand(cmd);
        assertTrue(method_2.getCommands().contains(cmd));
    }
}
