package ru.omsu.fctk.translator.emitter;

import java.io.IOException;

public interface IEmitter {
    public void emit(Object... data) throws IOException;
}
