package ru.omsu.fctk.translator.emitter;

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
     * @throws IllegalArgumentException если {@code method} уже хранится в эмиттере
     */
    public void addMethod(Method method){
        if(method == null) {
            throw new IllegalArgumentException("method is null");
        }
        if(methods.contains(method)) {
            throw new IllegalArgumentException("method is already stored in emitter");
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

    public void emit() throws IOException {
        writer.write(formatter.format(methods));
    };

    public List<Method> getMethods() {
        return  methods;
    }

}