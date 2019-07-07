package load.phone.app.validator;

import load.phone.app.exception.CountryNotFoundException;
import load.phone.app.exception.PhoneNumValidationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PhoneNumValidatorUnitTest {

    private PhoneNumValidator phoneNumValidator = new PhoneNumValidator();

    @Test
    public void testCheckPhoneValid() throws Exception {
        assertEquals("244233344", phoneNumValidator.checkPhone("244233344"));
    }

    @Test
    public void testCheckPhoneException() throws Exception {
        try {
            phoneNumValidator.checkPhone("[notes2]");
            fail();
        } catch (PhoneNumValidationException e) {
            assertEquals("PhoneNum: [notes2] Validation Failed! "
                    + "(including code should be > 8 && < 16", e.getMessage());
        }
    }

    @Test
    public void testCheckLoadResultValid() throws Exception {
        assertEquals("Latvia", phoneNumValidator.checkLoadResult("Latvia"));
    }

    @Test
    public void testCheckLoadResultException() throws Exception {
        try {
            phoneNumValidator.checkLoadResult("Unknown");
            fail();
        } catch (CountryNotFoundException e) {
            assertEquals("No country found: Unknown!", e.getMessage());
        }
    }

}
