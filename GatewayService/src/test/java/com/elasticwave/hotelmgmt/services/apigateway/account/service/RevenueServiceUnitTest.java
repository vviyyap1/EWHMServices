package com.elasticwave.hotelmgmt.services.apigateway.account.service;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.RevenueCategory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevenueServiceUnitTest {
    @Autowired
    private RevenueService revenueService;

    @MockBean
    private RestTemplate restTemplate;


    private List<RevenueCategory> revenueCategories;


    @Test
    public void testGetDailyRevenue() throws IOException{
        DailyHotelRevenue dailyHotelRevenue;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<DailyHotelRevenue> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/dailyhotelrevenue.json");
        dailyHotelRevenue = mapper.readValue(inputStream,typeReference);

        when(restTemplate.getForObject(eq("http://revenuequeryservice/api/account/revenue/dailyRevenue/1/on/07/28/2019"), any(Class.class))).thenReturn(dailyHotelRevenue, HttpStatus.OK);
        DailyHotelRevenue result = revenueService.getDailyRevenue("1", "07/28/2019");

        assertThat(result).isNotNull();
        assertThat(result).matches(x -> x.getHotelId().equals(dailyHotelRevenue.getHotelId()));
        assertThat(result).matches(x -> x.getCategoriesRevenue().size() == 3);
        assertThat(result).matches(x -> x.getCategoriesRevenueTree().getRevenue().equals(1210.0));
    }

    @Test
    public void testGetRevenue() throws IOException{
        HotelRevenue hotelRevenue;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HotelRevenue> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/hotelrevenue.json");
        hotelRevenue = mapper.readValue(inputStream,typeReference);

        when(restTemplate.getForObject(eq("http://revenuequeryservice/api/account/revenue/dailyRevenue/1/from/07/28/2019/to/07/29/2019"), any(Class.class))).thenReturn(hotelRevenue, HttpStatus.OK);
        HotelRevenue result = revenueService.getRevenue("1", "07/28/2019", "07/29/2019");

        assertThat(result).isNotNull();
        assertThat(result).matches(x -> x.getHotelId().equals(hotelRevenue.getHotelId()));
    }

    @Test
    public void testSaveDailyHotelRevenue(){

        when(restTemplate.postForObject(eq("http://revenuecommandservice/api/account/revenue/saveDailyHotelRevenue"),
                            any(), eq(String.class)))
                         .thenReturn("success");
        String result = revenueService.saveDailyHotelRevenue(new DailyHotelRevenue());

        assertThat(result).isNotNull().isNotEmpty().isEqualTo("success");
    }
}
