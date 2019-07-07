package load.phone.app.service;

import load.phone.app.resource.IStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
public class PhoneNumService {

    private final IStorage storage;

    @Autowired
    public PhoneNumService(IStorage storage) {
        this.storage = storage;
    }

    public Observable<Void> putPhone(String phoneCode, String country) {
        return storage.putPhone(phoneCode, country);
    }

    public Observable<String> getCountry(String phoneCode) {
        return storage.getCountry(phoneCode);
    }
}
