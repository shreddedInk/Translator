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
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class WriteStartTest {
    Emitter emitter;
    Method method_1;
    Method method_2;
    Method method_3;

    @Before
    public void initialize() {
        emitter = new Emitter(mock(Writer.class), Mockito.mock(IFormatter.class));

        method_1 = new Method("1", "1", new MethodOptions());
        method_2 = new Method("2", "2", new MethodOptions());
        method_3 = new Method("3", "3", new MethodOptions());
        emitter.addMethod(method_1);
        emitter.addMethod(method_2);
        emitter.addMethod(method_3);

        emitter.openMethod(method_1);
        emitter.addCommand(Mockito.mock(Command.class));
        emitter.addCommand(mock(Command.class));
        emitter.closeMethod();

        emitter.openMethod(method_2);
        emitter.addCommand(mock(Command.class));
        emitter.closeMethod();
    }

    @Test
    public void shouldEmptyMethodsList() {
        assertFalse(emitter.getMethods().isEmpty());
        emitter.writeStart();
        assertTrue(emitter.getMethods().isEmpty());

    }
    @Test
    public void shouldEmptyCommandsInsideStoredMethods() {

        assertFalse(method_1.getCommands().isEmpty());
        assertFalse(method_2.getCommands().isEmpty());
        assertTrue(method_3.getCommands().isEmpty());

        emitter.writeStart();
        assertTrue(method_1.getCommands().isEmpty());
        assertTrue(method_2.getCommands().isEmpty());
        assertTrue(method_3.getCommands().isEmpty());
    }

    @Test
    public void shouldNotFailWhenMethodsAreEmpty() {
        Emitter emitter = new Emitter(mock(Writer.class), mock(IFormatter.class));
    }
    @Test
    public void multipleCalls() {
        assertFalse(emitter.getMethods().isEmpty());
        emitter.writeStart();
        emitter.writeStart();
        emitter.writeStart();
        assertTrue(emitter.getMethods().isEmpty());
    }
    @Test
    public void shouldKeepSameMethodsInstance() {
        List<Method> before = emitter.getMethods();

        emitter.writeStart();
        assertSame(before, emitter.getMethods());
        assertTrue(emitter.getMethods().isEmpty());

    }
}