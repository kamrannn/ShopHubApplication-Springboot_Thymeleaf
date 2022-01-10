package cf.ac.uk.shophub.shophubapp.fullStack;

import cf.ac.uk.shophub.shophubapp.service.BusinessSearch;
import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FullStackTests {

    @Autowired
    private BusinessSearch businessSearch;

    @Autowired
    MockMvc mvc;

    @Test
    public void shouldGet4BusinessesAsDTO() throws Exception {
        List<BusinessDTO> businessDTOList = businessSearch.findAllBiz();
        assertEquals(4, businessDTOList.size());
    }

    @Test
    public void shouldGet4BusinessesAsJSON() throws Exception {
        mvc.perform(get("/api/discover").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    /**
     * This test checks multiple users and checks if they are subscribed to the expected amount of businesses.
     *
     * @param username
     * <p>The username that the API will query.</p>
     * @param expectedSizeAsString
     * <p>The size of how many businesses the test should expect the user to be subscribed to.
     * (stored as a string)</p>
     *
     */
    @ParameterizedTest
    @CsvSource({"test1,3","test2,2"})
    public void shouldGetNBusinessesFromUserSubscriptionsAsJSON(String username, String expectedSizeAsString) throws Exception {
        int expectedSize = Integer.parseInt(expectedSizeAsString);
        mvc.perform(get("/api/user?username=" + username).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedSize)));
    }
}
