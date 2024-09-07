import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class MedalInfoManager
{
    public final static String dataPath = "data/paris2024.json";
    public static MedalInfoModel info;
    public static void Initialize()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = null;

        try {
            file = new File(Objects.requireNonNull(OlympicSearch.class.getResource(dataPath)).toURI());
        } catch (URISyntaxException e) {
            System.out.println("无法读取数据文件：" + e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            info = objectMapper.readValue(file, MedalInfoModel.class);
        } catch (IOException e) {
            System.out.println("无法解析数据文件：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
