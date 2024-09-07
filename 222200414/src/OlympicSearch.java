import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OlympicSearch
{
    final static CommandHandler[] handlers =
    {
        new TotalCommandHandler()
    };
    final static Map<String, CommandHandler> handlerMap = new HashMap<>();

    public static void main(String[] args)
    {
        // 读取指令列表
        if (args.length != 2)
        {
            System.out.println("请正确传入参数：... <输入文件> <输出文件>");
            return;
        }

        // 初始化奖牌信息管理器
        MedalInfoManager.Initialize();

        // 初始化指令路由
        for(CommandHandler handler : handlers)
        {
            handlerMap.put(handler.getRoute(), handler);
        }

        // 处理指令列表
        File input = new File(args[0]);
        PrintWriter output = null;
        try
        {
            output = new PrintWriter(args[1]);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("写入输出数据失败：" + e.getMessage());
            return;
        }

        try (Scanner scanner = new Scanner(input))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] arg = line.split(" ");
                String route = arg[0];

                if (!handlerMap.containsKey(route))
                {
                    output.append("Error\n");
                }
                else
                {
                    handlerMap.get(route).process(output, arg);
                }

                output.append("-----\n");
            }
        }
        catch (IOException e)
        {
            System.out.println("输入信息读取失败：" + e.getMessage());
        }
    }
}