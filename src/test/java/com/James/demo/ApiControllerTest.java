package com.James.demo;

import com.James.demo.controller.ApiController;
import com.James.demo.entity.User;
import com.James.demo.service.UserService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ApiController apiController;

    @MockBean
    private UserService userService;

    private User user;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.apiController).build();// Standalone context

        user = new User();
        user.setName("name123");
        user.setPassword("password123");
    }

    @Test
    public void testAddUser() throws Exception {
        //Mocking
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "name123");
        jsonObject.put("password", "password123");

        String resultActions = mockMvc.perform(post("/api/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(jsonObject)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        when(this.userService.insertByUser(user)).thenReturn(user);
    }


    @Test
    public void testGetUserList() throws Exception {
        String resultActions = mockMvc.perform(get("/api")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testUpdateUser() throws Exception {
        //Mocking
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "1");
        jsonObject.put("name", "name321");
        jsonObject.put("password", "password321");

        String resultActions = mockMvc.perform(post("/api/update")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(jsonObject)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testDelete() throws Exception {
        String resultActions = mockMvc.perform(get("/api/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
