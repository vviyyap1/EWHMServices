package com.elasticwave.hotelmgmt.services.account.revenue.command.repository;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.DailyHotelRevenue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class DailyHotelRevenueRepositoryUnitTest {
    @Autowired
    private MongoTemplate  mongoTemplate;

    @Test
    public void testSaveAll() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DailyHotelRevenue>> hotelRevenueTypeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/hotelrevenue.json");
        List<DailyHotelRevenue> hotelRevenues = mapper.readValue(inputStream,hotelRevenueTypeReference);
        hotelRevenues.get(0).setDate(new SimpleDateFormat("MM-dd-yyyy").parse("07-28-2019"));
        hotelRevenues.get(1).setDate(new SimpleDateFormat("MM-dd-yyyy").parse("07-29-2019"));

        hotelRevenues.stream().forEach(mongoTemplate::save);

        assertThat(mongoTemplate.findAll(DailyHotelRevenue.class))
                .isNotNull()
                .matches(x -> x.size() == 2);

    }
}
