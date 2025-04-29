package ru.omsu.translator.emitter.formatter;

import org.junit.Before;
import org.junit.Test;
import ru.omsu.translator.emitter.Command;
import ru.omsu.translator.emitter.Formatter;
import ru.omsu.translator.emitter.Method;
import ru.omsu.translator.emitter.MethodOptions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormatTest {
    Formatter formatter;
    @Before
    public void initialize() {
        formatter = new Formatter("Test", 4);
    }
    @Test
    public void shouldFormatEmptyMethodsList() {
        List<Method> methods = new ArrayList<>();
        String expected = """
                .class public Test
                .super java/lang/Object
                
                .method public <init>()V
                    aload_0
                    invokespecial java/lang/Object/<init>()V
                    return
                .end method
                
                """;
        assertEquals(expected, formatter.format(methods));
    }
    @Test
    public void shouldFormatMethodsListWithOneMethod() {
        List<Method> methods = new ArrayList<>();

        MethodOptions options = new MethodOptions();
        options.accessModifiers = new String[]{"public", "static"};
        options.params = new String[]{"1", "2","3"};
        Method method = new Method("1","V", options);
        method.getCommands().add(new Command(".limit", "stack", "2"));
        method.getCommands().add(new Command("tableswitch", "0", "Label1", "Label2"));

        methods.add(method);

        String expected = """
                .class public Test
                .super java/lang/Object
                
                .method public <init>()V
                    aload_0
                    invokespecial java/lang/Object/<init>()V
                    return
                .end method
                .method public static 1(123)V
                    .limit stack 2
                    tableswitch 0
                        Label1
                        Label2
                .end method
                """;
    }
    @Test
    public void shouldFormatMethodsListWithMultipleMethods() {
        List<Method> methods = new ArrayList<>();

        MethodOptions options = new MethodOptions();
        options.accessModifiers = new String[]{"public", "static"};
        options.params = new String[]{"1", "2","3"};
        Method method_1 = new Method("1","V", options);
        method_1.getCommands().add(new Command(".limit", "stack", "2"));
        method_1.getCommands().add(new Command("tableswitch", "0", "Label1", "Label2"));
        Method method_2 = new Method("2", "V", new MethodOptions());
        method_2.getCommands().add(new Command(".limit", "stack", "2"));

        methods.add(method_1);
        methods.add(method_2);

        String expected = """
                .class public Test
                .super java/lang/Object
                
                .method public <init>()V
                    aload_0
                    invokespecial java/lang/Object/<init>()V
                    return
                .end method
                .method public static 1(123)V
                    .limit stack 2
                    tableswitch 0
                        Label1
                        Label2
                .end method
                .method 2()V
                    .limit stack 2
                .end method
                """;
    }
}
