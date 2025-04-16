package org.example.jasmincode;

import java.io.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Класс для генерации Jasmin-кода (ассемблера для JVM)
 */
public class Emitter implements IEmitter{
    private final Writer writer;
    private final IFormatter formatter;
    private final List<Method> methods = new ArrayList<>();
    private Method currentMethod;

    /**
     * Конструктор эмиттера
     * @param formatter объект реализации интерфейса {@code IFormatter}
     */
    public Emitter(Writer writer, IFormatter formatter){
        this.writer = writer;
        this.formatter = formatter;
    }

    /**
     * Очищает хранимые методы в эмиттере
     */
    public void writeStart(){
        for(Method method: methods) {
            method.getCommands().clear();
        }
        methods.clear();
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
        methods.add(method);
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
        if(!methods.contains(method)) throw new IllegalArgumentException("method was not added to emitter");
        currentMethod = method;
    }

    /**
     * Закрывает открытый метод
     * @throws IllegalStateException если ни один из методов не открыт
     */

    public void closeMethod() throws IllegalStateException {
        if(currentMethod == null) throw new IllegalStateException("no method is opened");
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
        currentMethod.addCommand(command);
    }

    public void emit(Object... data) throws IOException {
        writer.write(formatter.format(methods));
    };


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