package org.example.jasmincode;

import java.io.IOException;

public interface IEmitter {
    public void emit(Object... data) throws IOException;
}
