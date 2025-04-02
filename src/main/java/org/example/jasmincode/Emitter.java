package org.example.jasmincode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для генерации Jasmin-кода (ассемблера для JVM)
 */
public class Emitter {
    private final BufferedWriter writer;
    private final List<String> commands = new ArrayList<>();
    private boolean methodStarted = false;

    /**
     * Конструктор эмиттера
     * @param destinationFile путь к выходному файлу .j
     * @throws IOException если не удалось создать файл
     */
    public Emitter(String destinationFile) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(destinationFile));
    }

    /**
     * Записывает начало класса (заголовочную часть)
     * @throws IOException если произошла ошибка записи
     */
    public void writeStart() throws IOException {
        writer.write(".class public Generated\n");
        writer.write(".super java/lang/Object\n\n");

        writer.write(".method public <init>()V\n");
        writer.write("    aload_0\n");
        writer.write("    invokespecial java/lang/Object/<init>()V\n");
        writer.write("    return\n");
        writer.write(".end method\n\n");

        writer.write(".method public static main([Ljava/lang/String;)V\n");
        writer.write("    .limit stack 100\n");
        writer.write("    .limit locals 100\n");
        methodStarted = true;
    }

    /**
     * Добавляет команду в текущий метод
     * @param cmd имя команды (например, "getstatic")
     * @param cmdParams параметры команды (например, "java/lang/System/out Ljava/io/PrintStream;")
     */
    public void addCommand(String cmd, String... cmdParams) {
        StringBuilder command = new StringBuilder("    ").append(cmd);
        for (String param : cmdParams) {
            command.append(" ").append(param);
        }
        commands.add(command.toString());
    }

    /**
     * Завершает генерацию кода (записывает все команды и закрывает метод/класс)
     * @throws IOException если произошла ошибка записи
     */
    public void writeEnd() throws IOException {
        if (methodStarted) {
            if (commands.isEmpty() || !commands.get(commands.size() - 1).trim().equals("return")) {
                commands.add("    return");
            }

            for (String cmd : commands) {
                writer.write(cmd);
                writer.newLine();
            }

            writer.write(".end method\n");
            methodStarted = false;
        }
    }

    /**
     * Закрывает файл с кодом
     * @throws IOException если произошла ошибка при закрытии
     */
    public void close() throws IOException {
        writer.close();
    }
}