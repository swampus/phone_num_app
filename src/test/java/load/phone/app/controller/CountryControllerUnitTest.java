package load.phone.app.controller;

import com.google.common.collect.ImmutableList;
import load.phone.app.controller.rest.CountryByPhoneNumRequest;
import load.phone.app.controller.rest.CountryByPhoneNumResponse;
import load.phone.app.resource.model.CountryPhonecodeRecord;
import load.phone.app.service.FindCountryService;
import load.phone.app.service.LoadWikiDataService;
import load.phone.app.service.ParseService;
import load.phone.app.service.PhoneNumService;
import load.phone.app.validator.PhoneNumValidator;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryControllerUnitTest {

    @Mock
    private LoadWikiDataService loadWikiDataService;

    @Mock
    private ParseService parseService;

    @Mock
    private PhoneNumService phoneNumService;

    @Mock
    private FindCountryService findCountryService;

    @Mock
    private PhoneNumValidator phoneNumValidator;

    @InjectMocks
    private CountryController countryController;

    @Test
    public void testInitControllerLoadData() throws Exception {
        CountryPhonecodeRecord r1 = new CountryPhonecodeRecord("1", "2");
        CountryPhonecodeRecord r2 = new CountryPhonecodeRecord("11", "22");
        CountryPhonecodeRecord r3 = new CountryPhonecodeRecord("111", "222");

        Document document = mock(Document.class);

        when(loadWikiDataService.getWikiPageContent()).thenReturn(document);
        when(parseService.parseHtml(document)).thenReturn(ImmutableList.of(r1, r2, r3).stream());

        countryController.initControllerLoadData();

        verify(loadWikiDataService, times(1)).getWikiPageContent();
        verify(parseService, times(1)).parseHtml(document);

        verify(phoneNumService, times(1)).putPhone("1", "2");
        verify(phoneNumService, times(1)).putPhone("11", "22");
        verify(phoneNumService, times(1)).putPhone("111", "222");
    }

    @Test
    public void testGetCountryByPhoneNum() throws Exception {
        CountryByPhoneNumRequest countryByPhoneNumRequest = mock(CountryByPhoneNumRequest.class);

        when(phoneNumValidator.checkPhone("12345")).thenReturn("12345");
        when(phoneNumValidator.checkLoadResult("country")).thenReturn("country");
        when(countryByPhoneNumRequest.getPhoneNum()).thenReturn("12345");
        when(findCountryService.getCountryByPhoneCode("12345")).thenReturn(Observable.just("country"));

        CountryByPhoneNumResponse result = (CountryByPhoneNumResponse) countryController.getCountryByPhoneNum(countryByPhoneNumRequest);

        verify(findCountryService, times(1)).getCountryByPhoneCode("12345");
        verify(phoneNumValidator, times(1)).checkPhone("12345");
        verify(phoneNumValidator, times(1)).checkLoadResult("country");

        assertEquals("country", result.getCountry());
    }
}
