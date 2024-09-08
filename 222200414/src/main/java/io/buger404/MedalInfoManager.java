package io.buger404;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;

public class MedalInfoManager
{
    public static MedalInfoModel info;
    public static void initialize(InputStream dataStream)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try
        {
            info = objectMapper.readValue(dataStream, MedalInfoModel.class);
        }
        catch (IOException e)
        {
            System.out.println("无法解析数据文件：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
