package ru.omsu.fctk.translator.emitter.emitter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.omsu.fctk.translator.emitter.Emitter;
import ru.omsu.fctk.translator.emitter.IFormatter;
import ru.omsu.fctk.translator.emitter.Method;
import ru.omsu.fctk.translator.emitter.MethodOptions;

import java.io.Writer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddMethodTest {
    Emitter emitter;

    Method nullMethod;
    Method method_1;
    Method method_2;
    Method method_3;

    @Before
    public void initialize() {
        emitter = new Emitter(mock(Writer.class), Mockito.mock(IFormatter.class));
        method_1 = new Method("", "", new MethodOptions());
        method_2 = new Method("", "", new MethodOptions());
        method_3 = new Method("", "", new MethodOptions());
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfMethodIsNull() {
        emitter.addMethod(nullMethod);
    }
    @Test
    public void shouldAddMethod() {
        assertTrue(emitter.getMethods().isEmpty());
        emitter.addMethod(method_1);
        assertFalse(emitter.getMethods().isEmpty());
        assertTrue(emitter.getMethods().contains(method_1));
    }
    @Test
    public void multipleMethodsAdded() {
        assertTrue(emitter.getMethods().isEmpty());
        emitter.addMethod(method_1);
        emitter.addMethod(method_2);
        emitter.addMethod(method_3);
        assertFalse(emitter.getMethods().isEmpty());
        assertEquals(method_1, emitter.getMethods().get(0));
        assertEquals(method_2, emitter.getMethods().get(1));
        assertEquals(method_3, emitter.getMethods().get(2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfMethodIsAlreadyStored() {
        emitter.addMethod(method_1);
        emitter.addMethod(method_1);
    }
}
