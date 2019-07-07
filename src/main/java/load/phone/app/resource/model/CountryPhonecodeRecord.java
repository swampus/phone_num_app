package load.phone.app.resource.model;

public class CountryPhonecodeRecord {

	private String code;
	private String country;

	public CountryPhonecodeRecord(String code, String country) {
		this.code = code;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
