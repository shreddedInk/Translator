package ru.omsu.translator.emitter.formatter;

import org.junit.Test;
import ru.omsu.translator.emitter.Formatter;

import static org.junit.Assert.assertEquals;

public class FormatClassTest {
    @Test
    public void shouldFormatClassWithGivenName() {
        Formatter formatter = new Formatter("Test", 4);
        String expected = """
                .class public Test
                .super java/lang/Object
                
                .method public <init>()V
                    aload_0
                    invokespecial java/lang/Object/<init>()V
                    return
                .end method
                
                """;
        assertEquals(expected, formatter.formatClass());
    }
    @Test
    public void shouldFormatClassWithGivenIndent() {
        Formatter formatter = new Formatter("Test", 8);
        String expected = """
                .class public Test
                .super java/lang/Object
                
                .method public <init>()V
                        aload_0
                        invokespecial java/lang/Object/<init>()V
                        return
                .end method
                
                """;
        assertEquals(expected, formatter.formatClass());
    }
    @Test
    public void shouldFormatClassWithZeroIndent() {
        Formatter formatter = new Formatter("Test", 0);
        String expected = """
                .class public Test
                .super java/lang/Object
                
                .method public <init>()V
                aload_0
                invokespecial java/lang/Object/<init>()V
                return
                .end method
                
                """;
        assertEquals(expected, formatter.formatClass());
    }
}
