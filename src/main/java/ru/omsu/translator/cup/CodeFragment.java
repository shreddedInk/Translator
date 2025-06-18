package ru.omsu.translator.cup;

import ru.omsu.translator.java.type_control.TypeExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeFragment {
    private List<String> code;
    TypeExpression type;
    CodeFragment() {
        code = new ArrayList<>();
    }
    void add(String codeStr){
        code.add(codeStr);
    }
    void add(String... codeStrings) {
        String str = Arrays.stream(codeStrings)
                .reduce((s1, s2) -> s1 + " " + s2)
                .orElse("");
        code.add(str);
    }
    void add(CodeFragment codeFragment) {
        code.addAll(codeFragment.getCode());
    }
    void addToTheLast(String codeStr) {
        String lastStr = code.getLast();
        code.removeLast();
        code.add(lastStr + " " + codeStr);
    }
    List<String> getCode(){
        return code;
    }
    void setType(TypeExpression type) {
        this.type = type;
    }
    TypeExpression getType() {
        return type;
    }
}
