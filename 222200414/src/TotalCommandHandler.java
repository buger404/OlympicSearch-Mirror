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
        MedalInfoModel.CountryMedalInfo lastInfo = null;

        int rank = 1, displayRank = 1;

        for(MedalInfoModel.CountryMedalInfo countryMedalInfo : MedalInfoManager.info.countries)
        {
            if (lastInfo == null
                    || countryMedalInfo.medalNumber.gold != lastInfo.medalNumber.gold
                    || countryMedalInfo.medalNumber.silver != lastInfo.medalNumber.silver
                    || countryMedalInfo.medalNumber.bronze != lastInfo.medalNumber.bronze)
            {
                displayRank = rank;
            }
            output.println("rank" + displayRank + ":" + countryMedalInfo.name);
            output.println("gold:" + countryMedalInfo.medalNumber.gold);
            output.println("silver:" + countryMedalInfo.medalNumber.silver);
            output.println("bronze:" + countryMedalInfo.medalNumber.bronze);
            output.println("total:" + countryMedalInfo.medalNumber.total);
            output.println("-----");
            rank++;
            lastInfo = countryMedalInfo;
        }
    }
}
