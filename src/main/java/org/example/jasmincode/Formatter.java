package main.java.org.example.jasmincode;

import java.io.IOException;
import java.io.Writer;

public class Formatter implements IFormatter{
    private final Writer writer;
    private final String indent;

    public Formatter(Writer writer, int indent) {
        this.writer = writer;
        this.indent = " ".repeat(indent);;
    }
    public String getIndent() {
        return indent;
    }
    public void formatClass(String className) throws IOException {
        writer.write(".class public ");
        writer.write(className);
        writer.write("\n");
        writer.write(".super java/lang/Object\n\n");

        writer.write(".method public <init>()V\n");
        writer.write("    aload_0\n");
        writer.write("    invokespecial java/lang/Object/<init>()V\n");
        writer.write("    return\n");
        writer.write(".end method\n\n");
    }
    public void formatMethod(Method method) throws IOException {
        writer.write(".method ");
        writer.write(String.join(" ", method.getAccessModifiers()));
        if(method.getAccessModifiers().length > 0) writer.write(" ");
        writer.write(method.getName());
        writer.write("(");
        writer.write(String.join(" ", method.getParams()));
        writer.write(")");
        writer.write(method.getReturnType());
        writer.write("\n");
        for(Command cmd: method.getCommands()) {
            writer.write(indent);
            formatCommand(cmd);
        }
        writer.write(".end method\n");
    }

    public void formatCommand(Command cmd) throws IOException {
        switch (cmd.getInstruction()) {
            default:
                writer.write(cmd.getInstruction());
                writer.write(" ");
                writer.write(String.join(" ", cmd.getParams()));
                writer.write("\n");
        }
    }
}
