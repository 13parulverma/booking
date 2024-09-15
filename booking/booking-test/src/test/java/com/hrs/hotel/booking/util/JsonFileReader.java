package com.hrs.hotel.booking.util;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readJsonFile(String filePath, Class<T> valueType) throws IOException {
        return objectMapper.readValue(new File(filePath), valueType);
    }
}