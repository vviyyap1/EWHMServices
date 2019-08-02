package com.elasticwave.hotelmgmt.services.account.revenue.query.service;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.HotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.RevenueCategory;
import com.elasticwave.hotelmgmt.services.account.revenue.query.repository.DailyHotelRevenueRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.query.repository.RevenueCategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevenueServiceIntegrationTest {
    @Autowired
    private RevenueService revenueService;

    @Autowired
    private RevenueCategoryRepository revenueCategoryRepository;

    @Autowired
    private DailyHotelRevenueRepository dailyHotelRevenueRepository;

    @Before
    public void setup() throws Exception{
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
    public void testGetDailyRevenue() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<DailyHotelRevenue> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/dailyrevenue.json");
        DailyHotelRevenue dailyHotelRevenue = mapper.readValue(inputStream,typeReference);
        dailyHotelRevenueRepository.deleteAll();
        dailyHotelRevenueRepository.save(dailyHotelRevenue);

        Date date = new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019");
        DailyHotelRevenue result = revenueService.getDailyRevenue("1",date);
        assertThat(result).isNotNull().matches(x -> x.getCategoriesRevenueTree().getRevenue().equals(1110.0));
    }

    @Test
    public void testGetRevenue() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DailyHotelRevenue>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/hotelrevenue.json");
        List<DailyHotelRevenue> dailyHotelRevenues = mapper.readValue(inputStream,typeReference);
        dailyHotelRevenueRepository.deleteAll();
        dailyHotelRevenueRepository.saveAll(dailyHotelRevenues);

        Date from = new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019");
        Date to = new SimpleDateFormat("MM-dd-yyyy").parse("07-29-2019");
        HotelRevenue result = revenueService.getRevenue("1",from, to);
        assertThat(result).isNotNull().matches(x -> x.getDailyHotelRevenues().size() == 2)
                .matches(x -> x.getCategoriesRevenueTree().getRevenue().equals(1810.0));
    }

}
