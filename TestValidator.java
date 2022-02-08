import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestValidator {
    Validator validator = new Validator();

    @Test
    public void testValidate() {
        assertEquals(true, validator.validate("()"));
        assertEquals(true, validator.validate("[]"));
        assertEquals(true, validator.validate("{}"));
        assertEquals(true, validator.validate("(())"));
        assertEquals(true, validator.validate("[[]]"));
        assertEquals(true, validator.validate("{{}}"));
        assertEquals(false, validator.validate("("));
        assertEquals(false, validator.validate("["));
        assertEquals(false, validator.validate("{"));
        assertEquals(false, validator.validate(")"));
        assertEquals(false, validator.validate("]"));
        assertEquals(false, validator.validate("}"));
        assertEquals(false, validator.validate(")("));
        assertEquals(false, validator.validate("]["));
        assertEquals(false, validator.validate("}{"));
        assertEquals(false, validator.validate("()("));
        assertEquals(false, validator.validate("[]["));
        assertEquals(false, validator.validate("{}{"));
        assertEquals(false, validator.validate("(()"));
        assertEquals(false, validator.validate("[[]"));
        assertEquals(false, validator.validate("{{}"));
        assertEquals(false, validator.validate("())"));
        assertEquals(false, validator.validate("[]]"));
        assertEquals(false, validator.validate("{}}"));
        assertEquals(false, validator.validate("(]"));
        assertEquals(false, validator.validate("(}"));
        assertEquals(false, validator.validate("[)"));
        assertEquals(false, validator.validate("[}"));
        assertEquals(false, validator.validate("{)"));
        assertEquals(false, validator.validate("{]"));
        assertEquals(false, validator.validate("(])"));
        assertEquals(false, validator.validate("(})"));
        assertEquals(false, validator.validate("[)]"));
        assertEquals(false, validator.validate("[}]"));
        assertEquals(false, validator.validate("{)}"));
        assertEquals(false, validator.validate("{]}"));
    }

}
