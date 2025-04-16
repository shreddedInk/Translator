package org.example.jasmincode;

public class Command {
    private String instruction;
    private String[] params;
    public Command(String instruction, String... params) {
        this.instruction = instruction;
        this.params = params;
    }

    public String getInstruction() {
        return instruction;
    }

    public String[] getParams() {
        return params;
    }
}
