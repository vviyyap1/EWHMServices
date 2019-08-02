package com.elasticwave.hotelmgmt.services.account.revenue.command.service;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategory;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategoryTree;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevenueServiceIntegrationTest {
    @Autowired
    private RevenueService revenueService;

    @Test
    public void testSaveRevenueCategories() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<RevenueCategory>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/revenuecategories.json");
        List<RevenueCategory> revenueCategories = mapper.readValue(inputStream,typeReference);

        String result = revenueService.saveRevenueCategories(revenueCategories);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }

    @Test
    public void testSaveRevenueCategoriesTree() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<RevenueCategoryTree> typeReference2 = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/revenuecategoriestree.json");
        RevenueCategoryTree revenueCategoriesTree = mapper.readValue(inputStream,typeReference2);

        String result = revenueService.saveRevenueCategoriesTree(revenueCategoriesTree);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }

    @Test
    public void testSaveDailyHotelRevenues() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<DailyHotelRevenue> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/dailyrevenue.json");
        DailyHotelRevenue dailyHotelRevenue = mapper.readValue(inputStream,typeReference);

        String result = revenueService.saveDailyHotelRevenues(dailyHotelRevenue);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }

    @Test
    public void testSaveDailyHotelRevenues2() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DailyHotelRevenue>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/hotelrevenue.json");
        List<DailyHotelRevenue> dailyHotelRevenue = mapper.readValue(inputStream,typeReference);

        String result = revenueService.saveDailyHotelRevenues(dailyHotelRevenue);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }
}
