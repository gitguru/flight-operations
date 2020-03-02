package com.example.airport.flightoperations.config;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ConverterListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm";

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String MONTH_PATTERN = "yyyy-MM";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) { // root applicationContext

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            ConvertUtils.register(new Converter() {
                @Override
                public <T> T convert(Class<T> type, Object value) {

                    try {
                        Date date = DateUtils.parseDate((String) value, new String[]{DATETIME_PATTERN, DATE_PATTERN, MONTH_PATTERN});
                        return (T) date;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            }, Date.class);
        }
    }
}