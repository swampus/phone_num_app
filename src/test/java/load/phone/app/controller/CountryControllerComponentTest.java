package load.phone.app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerComponentTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCountryByPhoneNumOk() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.post("/phoneByNum")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "  \"phone_num\": \"1234567890\"\n"
                        + "}"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(result, "{\"country\":\"TestCountry\"}");
    }
}