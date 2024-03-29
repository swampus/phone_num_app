package load.phone.app.controller.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("country_by_phone_num_request")
public class CountryByPhoneNumRequest implements Response {

    @JsonProperty("phone_num")
    private String phoneNum;

    public CountryByPhoneNumRequest() {
    }

    public CountryByPhoneNumRequest(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
