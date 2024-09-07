package io.buger404;

import lombok.val;

import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;

public class ResultCommandHandler implements CommandHandler
{
    private final static LocalDate startDate = LocalDate.of(2024, 7, 27);
    private final static LocalDate endDate = LocalDate.of(2024, 8, 11);

    @Override
    public String getRoute()
    {
        return "result";
    }

    @Override
    public int getRequiredArgCount()
    {
        return 1;
    }

    private LocalDate parseArgDate(String[] args)
    {
        if (args[1].length() != 4)
        {
            System.out.println("输入的日期不正确：" + args[1]);
            return null;
        }

        String monthStr = args[1].substring(0, 2);
        String dayStr = args[1].substring(2, 4);

        int month = 1, day = 1;

        try
        {
            month = Integer.parseInt(monthStr);
        }
        catch (NumberFormatException ex)
        {
            System.out.println("输入的月份不正确：" + monthStr);
            return null;
        }

        try
        {
            day = Integer.parseInt(dayStr);
        }
        catch (NumberFormatException ex)
        {
            System.out.println("输入的日期不正确：" + dayStr);
            return null;
        }

        LocalDate date = null;

        try
        {
            date = LocalDate.of(2024, month, day);
        }
        catch (DateTimeException ex)
        {
            System.out.println("输入的日期不正确：" + args[1]);
            return null;
        }

        return date;
    }

    @Override
    public void process(PrintWriter output, String[] args)
    {
        LocalDate date = parseArgDate(args);

        if (date == null || date.isBefore(startDate) || date.isAfter(endDate))
        {
            output.println("N/A");
            output.println("-----");
            return;
        }

        MedalInfoModel.DateMedalInfo dateMedalInfo = MedalInfoUtils.getMedalInfoByDate(date);

        if (dateMedalInfo == null)
        {
            System.out.println("该日期没有任何比赛在进行");
            output.println("N/A");
            output.println("-----");
            return;
        }

        for(val info : dateMedalInfo.getCompetitors())
        {
            output.println("winner:" + info.getName());
            output.println("nationality:" + info.getNationality());
            output.println("gold:" + info.getMedalNumber().getGold());
            output.println("silver:" + info.getMedalNumber().getSilver());
            output.println("bronze:" + info.getMedalNumber().getBronze());
            output.println("total:" + info.getMedalNumber().getTotal());
            output.println("-----");
        }
    }
}
