package main.java.org.example.jasmincode;

import java.io.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для генерации Jasmin-кода (ассемблера для JVM)
 */
public class Emitter {
    private final Writer writer;
    private final Map<Method, List<Command>> methods = new HashMap<>();
    private final List<String> commands = new ArrayList<>();
    private Method currentMethod;

    /**
     * Конструктор эмиттера
     * @param writer открытый поток для записи кода
     */
    public Emitter(Writer writer){
        this.writer = writer;
    }

    /**
     * Очищает хранимые методы и команды
     */
    public void writeStart(){
        methods.clear();
        commands.clear();
    }

    /**
     * Добавляет метод в память эмиттера
     * @param method метод, добавляемый в память эмиттера
     * @throws IllegalArgumentException если {@code method} имеет значение null
     */
    public void addMethod(Method method){
        if(method == null) {
            throw new IllegalArgumentException("method is null");
        }
        methods.put(method, new ArrayList<>());
    }
    /**
     * Открывает метод для записи комманд
     * @param method метод, открываемый для записи комманд
     * @throws IllegalStateException если в эмиттере уже открыт один из методов
     * @throws IllegalArgumentException если {@code method} имеет значение null
     * @throws IllegalArgumentException если {@code method} не хранится в эмиттере
     */
    public void openMethod(Method method) throws IllegalStateException {
        if(!(currentMethod == null)) throw new IllegalStateException("current method is not closed");
        if(method == null) throw new IllegalArgumentException("method is null");
        if(!methods.containsKey(method)) throw new IllegalArgumentException("method was not added to emitter");
        currentMethod = method;
    }

    /**
     * Закрывает открытый метод
     * @param method закрываемый метод
     * @throws IllegalStateException если ни один из методов не открыт
     * @throws IllegalArgumentException если {@code method} имеет значение null
     * @throws IllegalArgumentException если {@code method} не хранится в эмиттере
     */

    public void closeMethod(Method method) throws IllegalStateException {
        if(currentMethod == null) throw new IllegalStateException("current method is not opened");
        if(method == null) throw new IllegalArgumentException("method is null");
        if(!methods.containsKey(method)) throw new IllegalArgumentException("method was not added to emitter");
        currentMethod = null;
    }

    /**
     * Добавляет команду в открытый метод
     * @param command добавляемая команда
     * @throws IllegalStateException если ни один из методов не открыт
     * @throws IllegalArgumentException если {@code command}  имеет значение null
     */

    public void addCommand(Command command) throws IllegalStateException {
        if(currentMethod == null) throw new IllegalStateException("no method is opened");
        if(command == null) throw new IllegalArgumentException("command is null");
        List<Command> commandList = methods.get(currentMethod);
        commandList.add(command);
    }

    /**
     * Закрывает поток с кодом
     * @throws IOException если произошла ошибка при закрытии
     */
    public void close() throws IOException {
        writer.close();
    }

    public void emit() {};
    private void writeMethod(Method method) {};


    /**
     * Записывает начало класса (заголовочную часть)
     * @throws IOException если произошла ошибка записи
     */
//    public void writeStart() throws IOException {
//        writer.write(".class public Generated\n");
//        writer.write(".super java/lang/Object\n\n");
//
//        writer.write(".method public <init>()V\n");
//        writer.write("    aload_0\n");
//        writer.write("    invokespecial java/lang/Object/<init>()V\n");
//        writer.write("    return\n");
//        writer.write(".end method\n\n");
//
//        writer.write(".method public static main([Ljava/lang/String;)V\n");
//        writer.write("    .limit stack 100\n");
//        writer.write("    .limit locals 100\n");
//        methodStarted = true;
//    }

    /**
     * Добавляет команду в текущий метод
     * @param cmd имя команды (например, "getstatic")
     * @param cmdParams параметры команды (например, "java/lang/System/out Ljava/io/PrintStream;")
     */
//    public void addCommand(String cmd, String... cmdParams) {
//        StringBuilder command = new StringBuilder("    ").append(cmd);
//        for (String param : cmdParams) {
//            command.append(" ").append(param);
//        }
//        commands.add(command.toString());
//    }

    /**
     * Завершает генерацию кода (записывает все команды и закрывает метод/класс)
     * @throws IOException если произошла ошибка записи
     */
//    public void writeEnd() throws IOException {
//        if (methodStarted) {
//            if (commands.isEmpty() || !commands.get(commands.size() - 1).trim().equals("return")) {
//                commands.add("    return");
//            }
//
//            for (String cmd : commands) {
//                writer.write(cmd);
//                writer.newLine();
//            }
//
//            writer.write(".end method\n");
//            methodStarted = false;
//        }
//    }
}