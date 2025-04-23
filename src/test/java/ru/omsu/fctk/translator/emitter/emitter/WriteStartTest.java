package ru.omsu.fctk.translator.emitter.emitter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.omsu.fctk.translator.emitter.Command;
import ru.omsu.fctk.translator.emitter.Emitter;
import ru.omsu.fctk.translator.emitter.IFormatter;
import ru.omsu.fctk.translator.emitter.Method;
import ru.omsu.fctk.translator.emitter.MethodOptions;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WriteStartTest {
    Emitter emitter;
    Method method_1;
    Method method_2;
    Method method_3;

    @Before
    public void initialize() {
        emitter = new Emitter(mock(Writer.class), Mockito.mock(IFormatter.class));

        method_1 = new Method("", "", new MethodOptions());
        method_2 = new Method("", "", new MethodOptions());
        method_3 = new Method("", "", new MethodOptions());
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
    @Test
    public void test() throws IOException {
        StringWriter writer = new StringWriter();
        IFormatter formatter = mock(IFormatter.class);
        Emitter emitter = new Emitter(writer, formatter);
        String expected = "formatted";
        List<Method> methods = new ArrayList<>();
        when(formatter.format(methods)).thenReturn(expected);
        emitter.emit();
        verify(formatter).format(methods);


        assertEquals(expected, writer.toString());
    }
}