package io.github.buger404;

public class OlympicSearch
{
    public static void main(String[] args)
    {
        // 读取指令列表
        if (args.length != 2)
        {
            System.out.println("请正确传入参数：... <输入文件> <输出文件>");
            return;
        }

        // 初始化奖牌信息管理器
        MedalInfoManager.initialize
        (
            OlympicSearch.class.getClassLoader().getResourceAsStream("paris2024.json")
        );

        // 初始化指令路由
        CommandManager.initialize();

        CommandManager.processCommand(args[0], args[1]);
    }
}