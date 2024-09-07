import java.io.PrintWriter;
import java.util.Comparator;

public class TotalCommandHandler implements CommandHandler
{
    @Override
    public String getRoute()
    {
        return "total";
    }

    @Override
    public void process(PrintWriter output, String[] args)
    {
        int rank = 1, displayRank = 1;

        MedalInfoManager.info.countries.sort
        (
            (o2, o1) ->
            {
                if (o1.medalNumber.gold != o2.medalNumber.gold)
                    return o1.medalNumber.gold - o2.medalNumber.gold;

                if (o1.medalNumber.silver != o2.medalNumber.silver)
                    return o1.medalNumber.silver - o2.medalNumber.silver;

                return o1.medalNumber.bronze - o2.medalNumber.bronze;
            }
        );

        MedalInfoModel.CountryMedalInfo lastInfo = null;

        for(MedalInfoModel.CountryMedalInfo countryMedalInfo : MedalInfoManager.info.countries)
        {
            if (lastInfo == null
                    || countryMedalInfo.medalNumber.gold == lastInfo.medalNumber.gold
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
