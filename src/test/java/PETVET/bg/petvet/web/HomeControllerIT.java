package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.entity.UserEntity;
import PETVET.bg.petvet.model.user.AppUserDetails;
import PETVET.bg.petvet.util.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private AppUserDetails userDetails = new AppUserDetails(
            "topsecret",
            "test@example.com",
            "Petar",
            "Petorv",
            new ArrayList<>(),
            true,
            false
    );

    @Test
    void testIndexPageShown() throws Exception {
        mockMvc.perform(get("/")).
                andExpect(status().isOk()).
                andExpect(view().name("index"));
    }

    @Test
    void testHomePageShown() throws  Exception {
        mockMvc.perform(get("/").with(user(userDetails))).
                andExpect(status().isOk()).
                andExpect(view().name("home"));
    }

}
