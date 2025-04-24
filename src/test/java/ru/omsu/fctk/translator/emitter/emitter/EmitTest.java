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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmitTest {
    Emitter emitter;
    IFormatter formatter;
    StringWriter writer;
    @Before
    public void initialize() {
        formatter = mock(IFormatter.class);
        writer = new StringWriter();
        emitter = new Emitter(writer, formatter);
    }
    @Test
    public void shouldCallFormatter() throws IOException {
        emitter.emit();
        verify(formatter).format(new ArrayList<>());
    }
    @Test
    public void shouldProvideToFormatterInternalData() throws IOException {
        Method method_1 = new Method("1", "1", new MethodOptions());
        Method method_2 = new Method("2", "2", new MethodOptions());
        Method method_3 = new Method("3", "3", new MethodOptions());
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

        emitter.emit();
        verify(formatter).format(emitter.getMethods());


    }
    @Test
    public void shouldCallWriter() throws IOException {
        Writer writer = mock(Writer.class);
        emitter = new Emitter(writer, formatter);

        String expected = "Formatted string";
        when(formatter.format(emitter.getMethods())).thenReturn(expected);
        emitter.emit();

        verify(writer).write(expected);
    }

    @Test
    public void shouldWriteFormattedString() throws IOException {
        String expected = "Formatted string";
        when(formatter.format(emitter.getMethods())).thenReturn(expected);
        emitter.emit();
        assertEquals(expected, writer.toString());
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOExceptionIfWriterFails() throws IOException {
        Writer failingWriter = mock(Writer.class);
        doThrow(new IOException("Write failed")).when(failingWriter).write(anyString());

        emitter = new Emitter(failingWriter, formatter);
        when(formatter.format(emitter.getMethods())).thenReturn("Some output");

        emitter.emit();
    }

}
