package com.elasticwave.hotelmgmt.services.account.revenue.query.controller;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.*;
import com.elasticwave.hotelmgmt.services.account.revenue.query.repository.DailyHotelRevenueRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.query.repository.RevenueCategoryRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.query.service.RevenueService;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    private RevenueService revenueService;

    @Autowired
    private RevenueCategoryRepository revenueCategoryRepository;

    @Autowired
    private DailyHotelRevenueRepository dailyHotelRevenueRepository;

    @Before
    public void setup() throws Exception{
        this.mockMvc = standaloneSetup(this.revenueController).build();
        this.basePath = "/api/account/revenue";
        //add revenue categories so we can get tree structure
        List<RevenueCategory> revenueCategories = revenueCategoryRepository.findAll();
        if(revenueCategories.size() > 0)
            return;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<RevenueCategory>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/revenuecategories.json");

        revenueCategories = mapper.readValue(inputStream,typeReference);
        revenueCategoryRepository.saveAll(revenueCategories);
    }

    @Test
    public void testGetDailyRevenue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<DailyHotelRevenue> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/dailyrevenue.json");
        DailyHotelRevenue dailyHotelRevenue = mapper.readValue(inputStream,typeReference);
        dailyHotelRevenueRepository.deleteAll();
        dailyHotelRevenueRepository.save(dailyHotelRevenue);

        mockMvc.perform(get(basePath + "/dailyRevenue/1/on/07-28-2019").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelId",is("1")))
                .andExpect(jsonPath("categoriesRevenueTree.revenue",is(1110.0)));
    }

    @Test
    public void testGetRevenue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DailyHotelRevenue>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/hotelrevenue.json");
        List<DailyHotelRevenue> dailyHotelRevenues = mapper.readValue(inputStream,typeReference);
        dailyHotelRevenueRepository.deleteAll();
        dailyHotelRevenueRepository.saveAll(dailyHotelRevenues);

        mockMvc.perform(get(basePath + "//dailyRevenue/1/from/07-28-2019/to/07-29-2019").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelId",is("1")))
                .andExpect(jsonPath("categoriesRevenueTree.revenue",is(1810.0)));
    }

    @Test
    public void testGetCategories() throws Exception {

        mockMvc.perform(get(basePath + "/categories/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("categoryId",is("0")));
    }


}
