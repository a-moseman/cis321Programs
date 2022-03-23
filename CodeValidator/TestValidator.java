import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestValidator {
    Validator validator = new Validator();

    @Test
    public void testValidate() {
        assertEquals(-1, validator.validate("()"));
        assertEquals(-1, validator.validate("[]"));
        assertEquals(-1, validator.validate("{}"));
        assertEquals(-1, validator.validate("(())"));
        assertEquals(-1, validator.validate("[[]]"));
        assertEquals(-1, validator.validate("{{}}"));
        assertEquals(1, validator.validate("("));
        assertEquals(1, validator.validate("["));
        assertEquals(1, validator.validate("{"));
        assertEquals(1, validator.validate(")"));
        assertEquals(1, validator.validate("]"));
        assertEquals(1, validator.validate("}"));
        assertEquals(1, validator.validate(")("));
        assertEquals(1, validator.validate("]["));
        assertEquals(1, validator.validate("}{"));
        assertEquals(1, validator.validate("()("));
        assertEquals(1, validator.validate("[]["));
        assertEquals(1, validator.validate("{}{"));
        assertEquals(1, validator.validate("(()"));
        assertEquals(1, validator.validate("[[]"));
        assertEquals(1, validator.validate("{{}"));
        assertEquals(1, validator.validate("())"));
        assertEquals(1, validator.validate("[]]"));
        assertEquals(1, validator.validate("{}}"));
        assertEquals(1, validator.validate("(]"));
        assertEquals(1, validator.validate("(}"));
        assertEquals(1, validator.validate("[)"));
        assertEquals(1, validator.validate("[}"));
        assertEquals(1, validator.validate("{)"));
        assertEquals(1, validator.validate("{]"));
        assertEquals(1, validator.validate("(])"));
        assertEquals(1, validator.validate("(})"));
        assertEquals(1, validator.validate("[)]"));
        assertEquals(1, validator.validate("[}]"));
        assertEquals(1, validator.validate("{)}"));
        assertEquals(1, validator.validate("{]}"));
        assertEquals(2, validator.validate("\n("));
        assertEquals(2, validator.validate("\n["));
        assertEquals(2, validator.validate("\n{"));
    }

}
