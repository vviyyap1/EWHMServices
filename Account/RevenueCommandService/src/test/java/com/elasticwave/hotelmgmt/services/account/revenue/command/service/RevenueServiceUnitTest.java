package com.elasticwave.hotelmgmt.services.account.revenue.command.service;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategory;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategoryTree;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.DailyHotelRevenueRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.RevenueCategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevenueServiceUnitTest {
    @Autowired
    private RevenueService revenueService;

    @MockBean
    private RevenueCategoryRepository revenueCategoryRepository;

    @MockBean
    private DailyHotelRevenueRepository dailyHotelRevenueRepository;

    @Test
    public void testSaveRevenueCategories() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<RevenueCategory>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/revenuecategories.json");
        List<RevenueCategory> revenueCategories = mapper.readValue(inputStream,typeReference);

        when(revenueCategoryRepository.saveAll(revenueCategories)).thenReturn(revenueCategories);

        String result = revenueService.saveRevenueCategories(revenueCategories);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }

    @Test
    public void testSaveRevenueCategoriesTree() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<RevenueCategoryTree> typeReference2 = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/revenuecategoriestree.json");
        RevenueCategoryTree revenueCategoriesTree = mapper.readValue(inputStream,typeReference2);

        when(revenueCategoryRepository.saveAll(any())).thenReturn(new ArrayList<>());

        String result = revenueService.saveRevenueCategoriesTree(revenueCategoriesTree);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }

    @Test
    public void testSaveDailyHotelRevenues() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<DailyHotelRevenue> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/dailyrevenue.json");
        DailyHotelRevenue dailyHotelRevenue = mapper.readValue(inputStream,typeReference);

        when(dailyHotelRevenueRepository.saveAll(any())).thenReturn(new ArrayList<>());

        String result = revenueService.saveDailyHotelRevenues(dailyHotelRevenue);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }

    @Test
    public void testSaveDailyHotelRevenues2() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DailyHotelRevenue>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/hotelrevenue.json");
        List<DailyHotelRevenue> dailyHotelRevenue = mapper.readValue(inputStream,typeReference);

        when(dailyHotelRevenueRepository.saveAll(any())).thenReturn(new ArrayList<>());

        String result = revenueService.saveDailyHotelRevenues(dailyHotelRevenue);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");

    }

}
