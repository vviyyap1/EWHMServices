package com.elasticwave.hotelmgmt.services.account.revenue.query.controller;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.*;
import com.elasticwave.hotelmgmt.services.account.revenue.query.service.RevenueService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
        this.basePath = "/api/account/revenue";
    }

    @Test
    public void testGetDailyRevenue() throws Exception {

        DailyHotelRevenue dailyHotelRevenue = new DailyHotelRevenue();
        dailyHotelRevenue.setHotelId("1");
        dailyHotelRevenue.setDate(new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019"));
        RevenueCategoryValueTree categoryValueTree = new RevenueCategoryValueTree();
        categoryValueTree.setRevenue(1110.0);
        dailyHotelRevenue.setCategoriesRevenueTree(categoryValueTree);

        when(revenueService.getDailyRevenue(any(),any())).thenReturn(dailyHotelRevenue);
        mockMvc.perform(get(basePath + "/dailyRevenue/1/on/07-28-2019").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelId",is("1")));
    }

    @Test
    public void testGetRevenue() throws Exception {

        HotelRevenue hotelRevenue = new HotelRevenue();
        hotelRevenue.setHotelId("1");
        hotelRevenue.setFrom(new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019"));
        hotelRevenue.setFrom(new SimpleDateFormat("MM-dd-yyyy").parse("07-29-2019"));
        RevenueCategoryValueTree categoryValueTree = new RevenueCategoryValueTree();
        categoryValueTree.setRevenue(1810.0);
        hotelRevenue.setCategoriesRevenueTree(categoryValueTree);

        when(revenueService.getRevenue(any(),any(),any())).thenReturn(hotelRevenue);
        mockMvc.perform(get(basePath + "//dailyRevenue/1/from/07-28-2019/to/07-29-2019").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelId",is("1")));
    }

    @Test
    public void testGetCategories() throws Exception {

        RevenueCategoryTree category = new RevenueCategoryTree();
        category.setCategoryId("0");
        category.setParentId("-1");

        when(revenueService.getCategories(any())).thenReturn(category);
        mockMvc.perform(get(basePath + "/categories/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("categoryId",is("0")));
    }

}
