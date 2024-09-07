import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Handler;

public class OlympicSearch
{
    final static CommandHandler[] handlers =
    {
        new TotalCommandHandler(), new ResultCommandHandler(), new PKCommandHandler()
    };
    final static Map<String, CommandHandler> handlerMap = new HashMap<>();

    private static void processCommandLine(String line, PrintWriter output)
    {
        String[] arg = line.split(" ");
        String route = arg[0];

        if (!handlerMap.containsKey(route))
        {
            output.println("Error");
            output.println("-----");
        }
        else
        {
            CommandHandler handler = handlerMap.get(route);
            if (handler.getRequiredArgCount() != arg.length - 1)
            {
                System.out.println("参数的个数不正确，预期：" + handler.getRequiredArgCount() +
                        "，实际：" + (arg.length - 1));
                output.println("Error");
                output.println("-----");
                return;
            }
            handlerMap.get(route).process(output, arg);
        }
    }

    private static void processCommand(String inFile, String outFile)
    {
        // 处理指令列表
        File input = new File(inFile);
        PrintWriter output = null;
        try
        {
            output = new PrintWriter(outFile);
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
                processCommandLine(scanner.nextLine(), output);
            }
            output.close();
        }
        catch (IOException e)
        {
            System.out.println("输入信息读取失败：" + e.getMessage());
        }
    }

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

        processCommand(args[0], args[1]);
    }
}