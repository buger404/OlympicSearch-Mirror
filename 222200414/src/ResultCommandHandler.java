import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.stream.Stream;

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

    private Vector<MedalInfoModel.DetachedCompetitorMedalInfo> getInfo(LocalDate date)
    {
        Vector<MedalInfoModel.DetachedCompetitorMedalInfo> infoVector = new Vector<>();

        for (MedalInfoModel.CompetitorMedalInfo competitorMedalInfo : MedalInfoManager.info.competitors)
        {
            MedalInfoModel.DateMedalInfo dateMedalInfo = null;

            for (MedalInfoModel.DateMedalInfo info : competitorMedalInfo.detail)
            {
                if (info.date.equals(date))
                {
                    dateMedalInfo = info;
                    break;
                }
            }

            if (dateMedalInfo == null)
            {
                continue;
            }

            MedalInfoModel.DetachedCompetitorMedalInfo info = new MedalInfoModel.DetachedCompetitorMedalInfo();
            info.name = competitorMedalInfo.name;
            info.nationality = competitorMedalInfo.nationality;
            info.medalNumber = dateMedalInfo.medalNumber;

            infoVector.add(info);
        }

        return infoVector;
    }

    @Override
    public void process(PrintWriter output, String[] args)
    {
        LocalDate date = parseArgDate(args);

        if (date == null)
        {
            output.println("Error");
            output.println("-----");
            return;
        }

        if (date.isBefore(startDate) || date.isAfter(endDate))
        {
            output.println("N/A");
            output.println("-----");
            return;
        }

        Vector<MedalInfoModel.DetachedCompetitorMedalInfo> infoVector = getInfo(date);

        MedalNumberComparator comparator = new MedalNumberComparator();
        infoVector.sort
        (
            (x, y) ->
            {
                if (x.medalNumber.equals(y.medalNumber))
                    return x.name.compareTo(y.name);
                return comparator.compare(x.medalNumber, y.medalNumber);
            }
        );

        for(MedalInfoModel.DetachedCompetitorMedalInfo info : infoVector)
        {
            output.println("winner:" + info.name);
            output.println("nationality:" + info.nationality);
            output.println("gold:" + info.medalNumber.gold);
            output.println("silver:" + info.medalNumber.silver);
            output.println("bronze:" + info.medalNumber.bronze);
            output.println("total:" + info.medalNumber.total);
            output.println("-----");
        }
    }
}
