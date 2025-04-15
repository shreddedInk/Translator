package main.java.org.example.jasmincode;

import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        HelloWorldTest();
    }
    public static void HelloWorldTest() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/java/org/example/jasmincode/test.j");
        Formatter formatter = new Formatter("Test", 4);
        Emitter emitter = new Emitter(fileWriter, formatter);

        emitter.writeStart();

        MethodOptions methodOptions = new MethodOptions();
        methodOptions.accessModifiers = new String[]{"public", "static"};
        methodOptions.params = new String[]{"[Ljava/lang/String;"};
        Method method = new Method("main", "V", methodOptions);
        emitter.addMethod(method);

        emitter.openMethod(method);
        emitter.addCommand(new Command(".limit stack", "2"));
        emitter.addCommand(new Command("getstatic", "java/lang/System/out", "Ljava/io/PrintStream;"));
        emitter.addCommand(new Command("ldc", "\"Hello World!\""));
        emitter.addCommand(new Command("invokevirtual", "java/io/PrintStream/println(Ljava/lang/String;)V"));
        emitter.addCommand(new Command("return"));
        emitter.closeMethod();

        emitter.emit("Test");
        fileWriter.close();
    }
}
