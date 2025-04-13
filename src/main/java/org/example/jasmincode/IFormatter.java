package main.java.org.example.jasmincode;

import java.io.IOException;

public interface IFormatter {
    public void formatClass(String className) throws IOException;
    public void formatMethod(Method method) throws IOException;
    public void formatCommand(Command command) throws IOException;
}
