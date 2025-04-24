package ru.omsu.fctk.translator.emitter.formatter;

import org.junit.Before;
import org.junit.Test;
import ru.omsu.fctk.translator.emitter.Command;
import ru.omsu.fctk.translator.emitter.Formatter;
import ru.omsu.fctk.translator.emitter.Method;
import ru.omsu.fctk.translator.emitter.MethodOptions;

import static org.junit.Assert.assertEquals;

public class FormatMethodTest {
    Formatter formatter;
    @Before
    public void initialize() {
        formatter = new Formatter("Test", 4);
    }
    @Test
    public void methodWithZeroNoModifiersAndParams() {
        Method method = new Method("1","V", new MethodOptions());
        String expected = """
                .method 1()V
                .end method
                """;
        assertEquals(expected, formatter.formatMethod(method));
    }
    @Test
    public void methodWithAccessModifiers() {
        MethodOptions options = new MethodOptions();
        options.accessModifiers = new String[]{"public", "static"};
        Method method = new Method("1","V", options);
        String expected = """
                .method public static 1()V
                .end method
                """;
        assertEquals(expected, formatter.formatMethod(method));
    }
    @Test
    public void methodWithParams() {
        MethodOptions options = new MethodOptions();
        options.accessModifiers = new String[]{"public", "static"};
        options.params = new String[]{"1", "2","3"};
        Method method = new Method("1","V", options);
        String expected = """
                .method public static 1(123)V
                .end method
                """;
        assertEquals(expected, formatter.formatMethod(method));
    }
    @Test
    public void methodWithOneCommand() {
        MethodOptions options = new MethodOptions();
        options.accessModifiers = new String[]{"public", "static"};
        options.params = new String[]{"1", "2","3"};
        Method method = new Method("1","V", options);
        method.getCommands().add(new Command(".limit", "stack", "2"));

        String expected = """
                .method public static 1(123)V
                    .limit stack 2
                .end method
                """;
        assertEquals(expected, formatter.formatMethod(method));
    }
    @Test
    public void methodWithMultipleCommands() {
        MethodOptions options = new MethodOptions();
        options.accessModifiers = new String[]{"public", "static"};
        options.params = new String[]{"1", "2","3"};
        Method method = new Method("1","V", options);
        method.getCommands().add(new Command(".limit", "stack", "2"));
        method.getCommands().add(new Command("tableswitch", "0", "Label1", "Label2"));

        String expected = """
                .method public static 1(123)V
                    .limit stack 2
                    tableswitch 0
                        Label1
                        Label2
                .end method
                """;
        assertEquals(expected, formatter.formatMethod(method));
    }
}
