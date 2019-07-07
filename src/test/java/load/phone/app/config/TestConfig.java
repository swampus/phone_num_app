package load.phone.app.config;

import load.phone.app.resource.HashMapStorage;
import load.phone.app.resource.IStorage;
import load.phone.app.service.FindCountryService;
import load.phone.app.service.PhoneNumService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class TestConfig {

    @Bean
    public PhoneNumService phoneNumService() {
        PhoneNumService phoneNumService = mock(PhoneNumService.class);
        when(phoneNumService.getCountry("1234567890")).thenReturn(Observable.just("TestCountry"));
        return phoneNumService;
    }

    @Bean
    public IStorage iStorage() {
        IStorage iStorage = mock(HashMapStorage.class);
        when(iStorage.getCountry("1234567890")).thenReturn(Observable.just("TestCountry"));
        return iStorage;
    }

    @Bean
    public FindCountryService findCountryService() {
        FindCountryService findCountryService = mock(FindCountryService.class);
        when(findCountryService.getCountryByPhoneCode("1234567890")).thenReturn(Observable.just("TestCountry"));
        return findCountryService;
    }
}
