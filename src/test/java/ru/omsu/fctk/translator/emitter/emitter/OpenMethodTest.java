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

public class OpenMethodTest {
    Emitter emitter;

    Method nullMethod;
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
    }
    @Test
    public void shouldOpenMethod(){
        emitter.openMethod(method_1);
        emitter.addCommand(Mockito.mock(Command.class));

        assertFalse(method_1.getCommands().isEmpty());
        assertTrue(method_2.getCommands().isEmpty());
        assertTrue(method_3.getCommands().isEmpty());
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfMethodIsNull() {
        emitter.openMethod(nullMethod);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfMethodIsNotStored() {
        emitter.openMethod(new Method("4", "", new MethodOptions()));
    }
    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfSameMethodIsOpened() {
        emitter.openMethod(method_1);
        emitter.openMethod(method_1);
    }
    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfOtherMethodIsOpened() {
        emitter.openMethod(method_1);
        emitter.openMethod(method_2);
    }
}

