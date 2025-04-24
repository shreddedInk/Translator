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

    Method method_1;
    Method method_2;
    Method method_3;

    @Before
    public void initialize() {
        emitter = new Emitter(mock(Writer.class), Mockito.mock(IFormatter.class));
        method_1 = new Method("1", "1", new MethodOptions());
        method_2 = new Method("2", "2", new MethodOptions());
        method_3 = new Method("3", "3", new MethodOptions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfMethodIsNull() {
        emitter.addMethod(null);
    }

    @Test
    public void shouldAddMethod() {
        assertTrue("Methods list should be empty before adding", emitter.getMethods().isEmpty());
        emitter.addMethod(method_1);
        assertFalse("Methods list should not be empty after adding", emitter.getMethods().isEmpty());
        assertTrue("Method should be in the list", emitter.getMethods().contains(method_1));
    }

    @Test
    public void shouldAddMultipleMethods() {
        assertTrue("Methods list should be empty before adding", emitter.getMethods().isEmpty());
        emitter.addMethod(method_1);
        emitter.addMethod(method_2);
        emitter.addMethod(method_3);

        assertEquals("First method should be method_1", method_1, emitter.getMethods().get(0));
        assertEquals("Second method should be method_2", method_2, emitter.getMethods().get(1));
        assertEquals("Third method should be method_3", method_3, emitter.getMethods().get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfMethodIsAlreadyStored() {
        emitter.addMethod(method_1);
        emitter.addMethod(method_1);
    }

    @Test
    public void shouldAddCopyOfStoredMethod() {
        Method copyOfMethod1 = new Method("", "", new MethodOptions());

        emitter.addMethod(method_1);
        emitter.addMethod(copyOfMethod1);

        assertEquals("Both methods should be stored since they are different objects",
                2, emitter.getMethods().size());
        assertTrue("Original method_1 should be present", emitter.getMethods().contains(method_1));
        assertTrue("Copy of method_1 should be present", emitter.getMethods().contains(copyOfMethod1));
        assertNotSame("Methods should be different objects", method_1, copyOfMethod1);
    }

}
