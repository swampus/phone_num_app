package load.phone.app.resource;

import rx.Observable;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage implements IStorage {

    private Map<String, String> storage = new HashMap<>();

    @Override
    public Observable<Void> putPhone(String phoneCode, String country) {
        return Observable.just(storage.put(phoneCode, country)).map(t -> null);
    }

    @Override
    public Observable<String> getCountry(String phoneCode) {
        return Observable.just(storage.get(phoneCode));
    }
}
