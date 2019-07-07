package load.phone.app.validator;

import load.phone.app.exception.CountryNotFoundException;
import load.phone.app.exception.PhoneNumValidationException;
import load.phone.app.resource.IStorage;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumValidator {
    public String checkPhone(String phoneNum) {
        if (!NumberUtils.isDigits(phoneNum)
                || phoneNum.length() < 8
                || phoneNum.length() > 15) {
            throw new PhoneNumValidationException("PhoneNum: " + phoneNum + " Validation Failed! "
                    + "(including code should be > 8 && < 16");
        }
        return phoneNum;
    }

    public String checkLoadResult(String country) {
        if (country.equals(IStorage.UNKNOWN_COUNTRY)) {
            throw new CountryNotFoundException("No country found: " + country + "!");
        }
        return country;
    }

}
