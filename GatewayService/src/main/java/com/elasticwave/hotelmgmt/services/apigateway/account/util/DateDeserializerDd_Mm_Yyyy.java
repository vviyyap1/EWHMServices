package com.elasticwave.hotelmgmt.services.apigateway.account.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializerDd_Mm_Yyyy extends StdDeserializer<Date> {
    private static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("MM-dd-yyyy");
    public DateDeserializerDd_Mm_Yyyy(){
        this(null);
    }
    public DateDeserializerDd_Mm_Yyyy(Class<?> c){
        super(c);
    }
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext
            deserializationContext) throws IOException {
        String dateStr = jsonParser.getText();
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}