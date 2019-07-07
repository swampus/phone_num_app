package load.phone.app.service;

import load.phone.app.resource.IStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindCountryServiceUnitTest {

    @Mock
    private IStorage iStorage;

    @InjectMocks
    private FindCountryService findCountryService;

    @Test
    public void testGetCountryByPhoneCodeResultFirst() throws Exception {
        when(iStorage.getCountry("1")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("123")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("1234")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12345")).thenReturn(Observable.just("c_5"));

        assertEquals("c_5", findCountryService.getCountryByPhoneCode("1234567890").toBlocking().first());

        when(iStorage.getCountry("1")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("123")).thenReturn(Observable.just("c_3"));
        when(iStorage.getCountry("1234")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12345")).thenReturn(Observable.just("c_5"));

        assertEquals("c_5", findCountryService.getCountryByPhoneCode("1234567890").toBlocking().first());
    }

    @Test
    public void testGetCountryByPhoneResultNotFirst() throws Exception {
        when(iStorage.getCountry("1")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("123")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("1234")).thenReturn(Observable.just("c_4A"));
        when(iStorage.getCountry("12345")).thenReturn(Observable.just(null));

        assertEquals("c_4A", findCountryService.getCountryByPhoneCode("1234567890").toBlocking().first());

        when(iStorage.getCountry("1")).thenReturn(Observable.just("C"));
        when(iStorage.getCountry("12")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("123")).thenReturn(Observable.just("c_3A"));
        when(iStorage.getCountry("1234")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12345")).thenReturn(Observable.just(null));

        assertEquals("c_3A", findCountryService.getCountryByPhoneCode("1234567890").toBlocking().first());
    }

    @Test
    public void testGetCountryByPhoneResultNotLast() throws Exception {
        when(iStorage.getCountry("1")).thenReturn(Observable.just("last"));
        when(iStorage.getCountry("12")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("123")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("1234")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12345")).thenReturn(Observable.just(null));
        assertEquals("last", findCountryService.getCountryByPhoneCode("1234567890").toBlocking().first());
    }

    @Test
    public void testGetCountryByPhoneResultNotFound() throws Exception {
        when(iStorage.getCountry("1")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("123")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("1234")).thenReturn(Observable.just(null));
        when(iStorage.getCountry("12345")).thenReturn(Observable.just(null));
        assertEquals("Unknown", findCountryService.getCountryByPhoneCode("1234567890").toBlocking().first());
    }

}
