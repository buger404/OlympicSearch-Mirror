package io.buger404;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;

public class MedalInfoManager
{
    public final static String dataPath = "paris2024.json";
    public static MedalInfoModel info;
    public static void Initialize()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        InputStream inputStream = OlympicSearch.class.getClassLoader().getResourceAsStream(dataPath);

        try
        {
            info = objectMapper.readValue(inputStream, MedalInfoModel.class);
        }
        catch (IOException e)
        {
            System.out.println("无法解析数据文件：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
