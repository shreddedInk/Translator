package ru.omsu.fctk.translator.emitter;

public class Command {
    private String instruction;
    private String[] params;
    public Command(String instruction, String... params) {
        this.instruction = instruction;
        this.params = params;
        switch(this.instruction) {
            case "lookupswitch":
                if(params.length <= 0) throw new IllegalArgumentException("Incorrect command");
                break;
            case "tableswitch":
                if(params.length <= 0) throw new IllegalArgumentException("Incorrect command");
                break;
        }
    }

    public String getInstruction() {
        return instruction;
    }

    public String[] getParams() {
        return params;
    }
}
