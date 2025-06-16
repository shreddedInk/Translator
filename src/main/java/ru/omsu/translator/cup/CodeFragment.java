package ru.omsu.translator.cup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeFragment {
    private List<String> code;
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
    List<String> getCode() {
        return code;
    }
}
