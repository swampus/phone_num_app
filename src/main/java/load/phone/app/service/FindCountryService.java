package load.phone.app.service;

import load.phone.app.resource.IStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Objects;

@Service
public class FindCountryService {

    private final IStorage storage;

    @Autowired
    public FindCountryService(IStorage storage) {
        this.storage = storage;
    }

    public Observable<String> getCountryByPhoneCode(String phoneCode) {
        return Observable.concat(
                storage.getCountry(phoneCode.substring(0, 5)),
                storage.getCountry(phoneCode.substring(0, 4)),
                storage.getCountry(phoneCode.substring(0, 3)),
                storage.getCountry(phoneCode.substring(0, 2)),
                storage.getCountry(phoneCode.substring(0, 1)))
                .filter(Objects::nonNull)
                .defaultIfEmpty(IStorage.UNKNOWN_COUNTRY);
    }

}
