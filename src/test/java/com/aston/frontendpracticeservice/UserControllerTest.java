package com.aston.frontendpracticeservice;

import com.aston.frontendpracticeservice.config.TestContainersConfig;
import com.aston.frontendpracticeservice.constants.ConstantsForTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.aston.frontendpracticeservice.constants.ConstantsForTest.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest extends TestContainersConfig {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCorrectListOfUsers() throws Exception {
        mockMvc.perform(get(URL_TO_GET_ALL_USERS))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(CORRECT_JSON_FOR_ALL_USERS));
    }

    @Test
    public void getEmptyUserLIst() throws Exception {
        mockMvc.perform(get(ConstantsForTest.NOT_CORRECT_URL))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(get(URL_TO_GET_USER_BY_ID + 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(CORRECT_JSON_FOR_USER));
    }

    @Test
    public void getUserByIncorrectId() throws Exception {
        mockMvc.perform(get(URL_TO_GET_USER_BY_ID + 50))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"));
    }

}
