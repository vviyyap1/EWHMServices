package com.elasticwave.utils.jsondeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializerDdSlashMmSlashYyyy extends StdDeserializer<Date> {
    private static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("MM/dd/yyyy");
    public DateDeserializerDdSlashMmSlashYyyy(){
        this(null);
    }
    public DateDeserializerDdSlashMmSlashYyyy(Class<?> c){
        super(c);
    }
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext
            deserializationContext) throws IOException, JsonProcessingException {
        String dateStr = jsonParser.getText();
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}