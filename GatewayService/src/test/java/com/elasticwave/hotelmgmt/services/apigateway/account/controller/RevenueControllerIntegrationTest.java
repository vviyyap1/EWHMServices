package com.elasticwave.hotelmgmt.services.apigateway.account.controller;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevenueControllerIntegrationTest {
    private MockMvc mockMvc;
    private String basePath;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RevenueController revenueController;

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(this.revenueController).build();
        this.basePath = "/api/gateway/account/revenue";
    }

    @Test
    public void testGetDailyHotelRevenue() throws Exception {
        mockMvc.perform(get(basePath + "/dailyRevenue/1/on/07-28-2019").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelId",is("1")));
    }

    @Test
    public void testGetHotelRevenue() throws Exception {
        mockMvc.perform(get(basePath + "/dailyRevenue/1/from/07-28-2019/to/07-29-2019").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelId",is("1")));
    }

    @Test
    public void testSaveDailyHotelRevenues() throws Exception {
        DailyHotelRevenue dailyHotelRevenue = new DailyHotelRevenue();
        dailyHotelRevenue.setHotelId("1");
        dailyHotelRevenue.setDate(new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019"));
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(dailyHotelRevenue);

        mockMvc.perform(
                post(basePath +"/saveDailyHotelRevenue").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
