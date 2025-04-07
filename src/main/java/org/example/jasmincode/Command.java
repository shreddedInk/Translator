package main.java.org.example.jasmincode;

public class Command {
    String instruction;
    String[] params;
    public Command(String instruction, String... params) {
        this.instruction = instruction;
        this.params = params;
    }
}
