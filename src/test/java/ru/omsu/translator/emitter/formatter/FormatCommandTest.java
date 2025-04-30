package ru.omsu.translator.emitter.formatter;

import org.junit.Before;
import org.junit.Test;

import ru.omsu.translator.emitter.Command;
import ru.omsu.translator.emitter.Formatter;

import static org.junit.Assert.*;

public class FormatCommandTest {
    Formatter formatter;
    @Before
    public void initialize() {
        formatter = new Formatter("Test", 4);
    }
    @Test
    public void oneLineCommandFormatting() {
        Command command = new Command("bipush", "10");
        String expected = "bipush 10\n";
        assertEquals(expected, formatter.formatCommand(command));
    }
    @Test
    public void oneLineCommandWithNoParametersFormatting() {
        Command command = new Command("aaload");
        String expected = "aaload\n";
        assertEquals(expected, formatter.formatCommand(command));
    }
    @Test
    public void lookupswitchCommandWithOneParam() {
        Command command = new Command("lookupswitch", "Label1 : aaload");
        String expected = """
        lookupswitch
            Label1 : aaload\n""";
        assertEquals(expected, formatter.formatCommand(command));
    }
    @Test
    public void lookupswitchCommandWithTwoParams() {
        Command command = new Command("lookupswitch", "Label1 : aaload", "default : DefaultLabel");
        String expected = """
        lookupswitch
            Label1 : aaload
            default : DefaultLabel\n""";
        assertEquals(expected, formatter.formatCommand(command));
    }
    @Test
    public void tableswitchCommandWithOneParam() {
        Command command = new Command("tableswitch", "0");
        String expected = """
        tableswitch 0\n""";
        assertEquals(expected, formatter.formatCommand(command));
    }
    @Test
    public void tableswitchCommandWithTwoParams() {
        Command command = new Command("tableswitch", "0", "Label1");
        String expected = """
        tableswitch 0
            Label1\n""";
        assertEquals(expected, formatter.formatCommand(command));
    }
    @Test
    public void tableswitchCommandWithThreeParams() {
        Command command = new Command("tableswitch", "0", "Label1", "default : DefaultLabel");
        String expected = """
        tableswitch 0
            Label1
            default : DefaultLabel\n""";
        assertEquals(expected, formatter.formatCommand(command));
    }

}
