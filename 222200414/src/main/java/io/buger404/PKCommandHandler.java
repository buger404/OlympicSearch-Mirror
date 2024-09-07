package io.buger404;

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
            output.println(country1.getName() + "VS" + country2.getName() + " " + args[3]);
            output.println("gold:" + event1.getMedalNumber().getGold() + "/" + event2.getMedalNumber().getGold());
            output.println("silver:" + event1.getMedalNumber().getSilver() + "/" + event2.getMedalNumber().getSilver());
            output.println("bronze:" + event1.getMedalNumber().getBronze() + "/" + event2.getMedalNumber().getBronze());
            output.println("total:" + event1.getMedalNumber().getTotal() + "/" + event2.getMedalNumber().getTotal());
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
