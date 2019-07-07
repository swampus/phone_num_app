package load.phone.app.resource;

import rx.Observable;

public interface IStorage {

	String UNKNOWN_COUNTRY = "Unknown";

	Observable<Void> putPhone(String phoneCode, String country);

	Observable<String> getCountry(String phoneCode);
}
