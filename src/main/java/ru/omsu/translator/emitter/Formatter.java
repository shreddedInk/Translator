package ru.omsu.translator.emitter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Formatter implements IFormatter {
    private final String className;
    private final String indent;

    public Formatter(String className, int indent) {
        this.className = className;
        this.indent = " ".repeat(indent);
    }

    public String getIndent() {
        return indent;
    }

    public String formatClass() {
        StringBuilder classString = new StringBuilder();
        classString.append(".class public ");
        classString.append(className);
        classString.append("\n");
        classString.append(".super java/lang/Object\n\n");

        classString.append(".method public <init>()V\n");
        classString.append("    aload_0\n");
        classString.append("    invokespecial java/lang/Object/<init>()V\n");
        classString.append("    return\n");
        classString.append(".end method\n\n");
        return classString.toString();
    }

    public String formatMethod(Method method) {
        StringBuilder methodString = new StringBuilder();
        methodString.append(".method ");
        methodString.append(String.join(" ", method.getAccessModifiers()));
        if(method.getAccessModifiers().length > 0) methodString.append(" ");
        methodString.append(method.getName());
        methodString.append("(");
        methodString.append(String.join("", method.getParams()));
        methodString.append(")");
        methodString.append(method.getReturnType());
        methodString.append("\n");
        for(Command cmd: method.getCommands()) {
            methodString.append(indent);
            methodString.append(formatCommand(cmd).replaceAll("(?m)^(?=\\s)", indent));
        }
        methodString.append(".end method\n");
        return methodString.toString();
    }

    public String formatCommand(Command cmd){
        StringBuilder cmdString = new StringBuilder();
        switch (cmd.getInstruction()) {
            case "lookupswitch":
                cmdString.append(cmd.getInstruction());
                cmdString.append("\n");
                for(int i = 0; i < cmd.getParams().length; i++) {
                    cmdString.append(indent);
                    cmdString.append(cmd.getParams()[i]);
                    cmdString.append("\n");
                }
                break;
            case "tableswitch":
                cmdString.append(cmd.getInstruction());
                cmdString.append(" ");
                cmdString.append(cmd.getParams()[0]);
                cmdString.append("\n");
                for(int i = 1; i < cmd.getParams().length; i++) {
                    cmdString.append(indent);
                    cmdString.append(cmd.getParams()[i]);
                    cmdString.append("\n");
                }
                break;
            default:
                cmdString.append(cmd.getInstruction());
                if(cmd.getParams().length > 0) cmdString.append(" ");
                cmdString.append(String.join(" ", cmd.getParams()));
                cmdString.append("\n");
        }
        return cmdString.toString();
    }

    @Override
    public String format(Object... data) {
        StringBuilder formatStr = new StringBuilder();
        formatStr.append(formatClass());
        for(Object object: data) {
            if(object instanceof Collection<?> col) {
                if(!col.isEmpty() && col.iterator().next() instanceof Method) {
                    for(Method method: (Collection<Method>) col) {
                        formatStr.append(formatMethod(method));
                        formatStr.append("\n");
                    }
                }
            }
        }
        return formatStr.toString();
    }
}
