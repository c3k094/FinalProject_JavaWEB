package PETVET.bg.petvet.web;

import PETVET.bg.petvet.model.entity.OwnerEntity;
import PETVET.bg.petvet.model.user.AppUserDetails;
import PETVET.bg.petvet.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private AppUserDetails userDetails;

    private OwnerEntity testOwner;

    @BeforeEach
    void setUp() {
        userDetails = new AppUserDetails(
                "topsecret",
                "test@example.com",
                "Petar",
                "Petorv",
                new ArrayList<>(),
                true,
                false
        );
        var testAddress = testDataUtils.createTestAddress("Bulgaria", "sofia", "Street 5");
        testOwner = testDataUtils.createTestOwner("i.ivanov@mail.bg","0886351532", testAddress);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    void testAddOwnerPageShown() throws Exception {
        mockMvc.perform(get("/owners/add").with(user(userDetails))).
                andExpect(status().isOk()).
                andExpect(view().name("owner-add"));
    }

    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN"}
    )
    void testDelete() throws Exception {
        mockMvc.perform(delete("/owners/delete/{id}", testOwner.getId()).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/owners/all"));
    }

    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN"}
    )
    void testEditPatientPageShown() throws Exception {
        mockMvc.perform(get("/owners/edit/{id}", testOwner.getId()).with(user(userDetails))).
                andExpect(status().isOk()).
                andExpect(view().name("owner-edit"));
    }

}
