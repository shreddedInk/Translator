package main.java.org.example.jasmincode;

import java.util.ArrayList;
import java.util.List;

public class Method{
    private String name;
    private String returnType;
    private String[] accessModifiers;
    private String[] params;
    private List<Command> commands;

    public Method(String name, String returnType, MethodOptions methodOptions) {
        this.name = name;
        this.returnType = returnType;
        this.accessModifiers = methodOptions.accessModifiers;
        this.params = methodOptions.params;
        commands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public String[] getAccessModifiers() {
        return accessModifiers;
    }

    public String[] getParams() {
        return params;
    }
    public List<Command> getCommands() {
        return commands;
    }
    public void addCommand(Command cmd) {
        commands.add(cmd);
    }
}
