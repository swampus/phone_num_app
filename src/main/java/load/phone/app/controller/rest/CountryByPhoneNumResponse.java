package load.phone.app.controller.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("country_by_phone_num_response")
public class CountryByPhoneNumResponse implements Response {

    @JsonProperty("country")
    private String country;

    public CountryByPhoneNumResponse() {
    }

    public CountryByPhoneNumResponse(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
