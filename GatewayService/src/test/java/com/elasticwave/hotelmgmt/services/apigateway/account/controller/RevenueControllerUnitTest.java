package com.elasticwave.hotelmgmt.services.apigateway.account.controller;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.service.RevenueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevenueControllerUnitTest {
    private MockMvc mockMvc;
    private String basePath;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RevenueController revenueController;

    @MockBean
    private RevenueService revenueService;

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(this.revenueController).build();
        this.basePath = "/api/gateway/account/revenue";
    }

    @Test
    public void testGetDailyHotelRevenue() throws Exception {

        DailyHotelRevenue dailyHotelRevenue = new DailyHotelRevenue();
        dailyHotelRevenue.setHotelId("1");
        dailyHotelRevenue.setDate(new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019"));

        when(revenueService.getDailyRevenue(eq("1"),eq("07-28-2019"))).thenReturn(dailyHotelRevenue);

        mockMvc.perform(get(basePath + "/dailyRevenue/1/on/07-28-2019").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelId",is("1")));
    }

    @Test
    public void testGetHotelRevenue() throws Exception {

        HotelRevenue hotelRevenue = new HotelRevenue();
        hotelRevenue.setHotelId("1");
        hotelRevenue.setFrom(new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019"));
        hotelRevenue.setTo(new SimpleDateFormat("MM-dd-yyyy").parse("07-29-2019"));

        when(revenueService.getRevenue(eq("1"),eq("07-28-2019"), eq("07-29-2019"))).thenReturn(hotelRevenue);

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

        when(revenueService.saveDailyHotelRevenue(any())).thenReturn("success");
        mockMvc.perform(
                post(basePath +"/saveDailyHotelRevenue").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
