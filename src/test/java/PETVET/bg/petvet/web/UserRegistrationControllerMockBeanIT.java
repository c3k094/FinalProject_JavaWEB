package PETVET.bg.petvet.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerMockBeanIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }

    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                        param("email", "ivan@example.com").
                        param("firstName", "Ivan").
                        param("lastName", "Ivanov").
                        param("password", "topsecret").
                        param("confirmPassword", "topsecret").
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/"));
    }


}
