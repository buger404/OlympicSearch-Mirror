import java.io.PrintWriter;

public class TotalCommandHandler implements CommandHandler
{
    @Override
    public String getRoute()
    {
        return "total";
    }

    @Override
    public int getRequiredArgCount()
    {
        return 0;
    }

    @Override
    public void process(PrintWriter output, String[] args)
    {
        for(MedalInfoModel.RankedCountryMedalInfo info : MedalInfoUtils.getRankedCountryMedalInfo())
        {
            output.println("rank" + info.rank + ":" + info.name);
            output.println("gold:" + info.medalNumber.gold);
            output.println("silver:" + info.medalNumber.silver);
            output.println("bronze:" + info.medalNumber.bronze);
            output.println("total:" + info.medalNumber.total);
            output.println("-----");
        }
    }
}
