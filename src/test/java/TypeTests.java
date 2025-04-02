import org.example.java.TypesControl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TypeTests {
    @Test
    public void test(){
        TypesControl typesControl = TypesControl.getTypesControl();
        typesControl.addNode("integer");
        typesControl.addNode("pointer_to_integer","integer");
        typesControl.addNode("real");
        typesControl.addNode("record A", Map.of("x","integer","y","real"));
        typesControl.addNode("record B", Map.of("x","integer","y","real"));

        System.out.println(typesControl);
        assertNotEquals(typesControl.getNode("record A"),typesControl.getNode("record B"));

    }
}
