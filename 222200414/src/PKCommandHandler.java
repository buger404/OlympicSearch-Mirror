import java.io.PrintWriter;

public class PKCommandHandler implements CommandHandler
{
    @Override
    public String getRoute()
    {
        return "PK";
    }

    @Override
    public int getRequiredArgCount()
    {
        return 3;
    }

    @Override
    public void process(PrintWriter output, String[] args)
    {
        try
        {
            MedalInfoModel.CountryMedalInfo country1 = MedalInfoUtils.getCountryInfo(args[1]);
            MedalInfoModel.CountryMedalInfo country2 = MedalInfoUtils.getCountryInfo(args[2]);
            MedalInfoModel.EventMedalInfo event1 = MedalInfoUtils.getEventInfo(country1, args[3]);
            MedalInfoModel.EventMedalInfo event2 = MedalInfoUtils.getEventInfo(country2, args[3]);
            output.println(country1.name + "VS" + country2.name + " " + args[3]);
            output.println("gold:" + event1.medalNumber.gold + "/" + event2.medalNumber.gold);
            output.println("silver:" + event1.medalNumber.silver + "/" + event2.medalNumber.silver);
            output.println("bronze:" + event1.medalNumber.bronze + "/" + event2.medalNumber.bronze);
            output.println("total:" + event1.medalNumber.total + "/" + event2.medalNumber.total);
            output.println("-----");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            output.println("Error");
            output.println("-----");
        }
    }
}
