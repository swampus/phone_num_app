package load.phone.app.controller;

import com.google.common.annotations.VisibleForTesting;
import load.phone.app.controller.rest.CountryByPhoneNumRequest;
import load.phone.app.controller.rest.CountryByPhoneNumResponse;
import load.phone.app.controller.rest.Response;
import load.phone.app.service.FindCountryService;
import load.phone.app.service.LoadWikiDataService;
import load.phone.app.service.ParseService;
import load.phone.app.service.PhoneNumService;
import load.phone.app.validator.PhoneNumValidator;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class CountryController {

    private final LoadWikiDataService loadWikiDataService;
    private final ParseService parseService;
    private final PhoneNumService phoneNumService;
    private final PhoneNumValidator phoneNumValidator;
    private final FindCountryService findCountryService;

    @Autowired
    public CountryController(LoadWikiDataService loadWikiDataService,
                             ParseService parseService, PhoneNumService phoneNumService,
                             PhoneNumValidator phoneNumValidator,
                             FindCountryService findCountryService) {
        this.loadWikiDataService = loadWikiDataService;
        this.parseService = parseService;
        this.phoneNumService = phoneNumService;
        this.phoneNumValidator = phoneNumValidator;
        this.findCountryService = findCountryService;
    }

    @PostConstruct
    @VisibleForTesting
    void initControllerLoadData() {
        Document document = loadWikiDataService.getWikiPageContent();
        parseService.parseHtml(document)
                .forEach(t -> {
                    phoneNumService.putPhone(t.getCode(),
                            t.getCountry());
                });
    }

    @RequestMapping(value = "/phoneByNum",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = {"Accept=" + MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:8080")
    public Response getCountryByPhoneNum(
            @RequestBody CountryByPhoneNumRequest countryByPhoneNumRequest) {
        String phoneNum = countryByPhoneNumRequest.getPhoneNum();
        return createCountryByPhoneNumResponse(phoneNum);
    }

    private CountryByPhoneNumResponse createCountryByPhoneNumResponse(String phoneNum) {
        return new CountryByPhoneNumResponse(findCountryService
                .getCountryByPhoneCode(phoneNumValidator.checkPhone(phoneNum))
                .map(phoneNumValidator::checkLoadResult).toBlocking().first());
    }

}
