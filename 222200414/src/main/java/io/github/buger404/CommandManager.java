package io.github.buger404;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager
{
    final static CommandHandler[] handlers =
    {
        new TotalCommandHandler(), new ResultCommandHandler(), new PKCommandHandler()
    };
    final static Map<String, CommandHandler> handlerMap = new HashMap<>();

    public static void initialize()
    {
        for(CommandHandler handler : handlers)
        {
            handlerMap.put(handler.getRoute(), handler);
        }
    }

    public static void processCommandLine(String line, PrintWriter output)
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

    public static void processCommand(String inFile, String outFile)
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

        try (Scanner scanner = new Scanner(input,"UTF-8"))
        {
            while (scanner.hasNextLine())
            {
                processCommandLine(scanner.nextLine(), output);
            }
            output.close();
        }
        catch (IOException e)
        {
            output.close();
            System.out.println("输入信息读取失败：" + e.getMessage());
        }
    }
}
