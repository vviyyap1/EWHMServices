package com.elasticwave.hotelmgmt.services.account.revenue.command.controller;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategory;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategoryTree;
import com.elasticwave.hotelmgmt.services.account.revenue.command.service.RevenueService;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @Autowired
    private RevenueService revenueService;

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(this.revenueController).build();
        this.basePath = "/api/account/revenue";
    }

    @Test
    public void testSaveRevenueCategories() throws Exception {

        List<RevenueCategory> rl = new ArrayList<>();
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(rl);

        mockMvc.perform(
                post(basePath +"/saveRevenueCategories").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testSaveRevenueCategoriesTree() throws Exception {

        RevenueCategoryTree revenueCategoryTree = new RevenueCategoryTree();
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(revenueCategoryTree);

        mockMvc.perform(
                post(basePath +"/saveRevenueCategoriesTree").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testSaveDailyHotelRevenues() throws Exception {

        DailyHotelRevenue dailyHotelRevenue = new DailyHotelRevenue();
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(dailyHotelRevenue);

        mockMvc.perform(
                post(basePath +"/saveDailyHotelRevenue").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testSaveDailyHotelRevenues2() throws Exception {

        List<DailyHotelRevenue> dailyHotelRevenue = new ArrayList<>();
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(dailyHotelRevenue);

        mockMvc.perform(
                post(basePath +"/saveMultipleDailyHotelRevenues").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
