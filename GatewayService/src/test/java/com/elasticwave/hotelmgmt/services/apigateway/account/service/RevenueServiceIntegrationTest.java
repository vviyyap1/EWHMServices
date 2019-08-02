package com.elasticwave.hotelmgmt.services.apigateway.account.service;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevenueServiceIntegrationTest {
    @Autowired
    private RevenueService revenueService;

    @Test
    public void testGetDailyRevenue() throws Exception{

        DailyHotelRevenue result = revenueService.getDailyRevenue("1", "07-28-2019");
        Date resultDate = new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019");

        assertThat(result).isNotNull()
        .matches(x -> x.getHotelId().equals("1"));
    }

    @Test
    public void testGetRevenue() {
        HotelRevenue result = revenueService.getRevenue("1", "07-28-2019", "07-29-2019");

        assertThat(result).isNotNull();
        assertThat(result).matches(x -> x.getHotelId().equals("1"));
    }

    @Test
    public void testSaveDailyHotelRevenue() throws Exception{
        DailyHotelRevenue dailyHotelRevenue = new DailyHotelRevenue();
        dailyHotelRevenue.setHotelId("1");
        dailyHotelRevenue.setDate(new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019"));

        String result = revenueService.saveDailyHotelRevenue(dailyHotelRevenue);

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");
    }
}
