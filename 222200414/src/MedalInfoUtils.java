import java.time.LocalDate;
import java.util.Vector;

public class MedalInfoUtils
{
    private static Vector<MedalInfoModel.RankedCountryMedalInfo> rankedCountryMedalInfos = null;

    public static MedalInfoModel.DateMedalInfo getMedalInfoByDate(LocalDate date)
    {
        return MedalInfoManager.info.dates.stream()
                                        .filter(x -> x.date.equals(date))
                                        .findFirst()
                                        .orElse(null);
    }

    public static Vector<MedalInfoModel.RankedCountryMedalInfo> getRankedCountryMedalInfo()
    {
        if (rankedCountryMedalInfos != null)
        {
            return rankedCountryMedalInfos;
        }

        rankedCountryMedalInfos = new Vector<>();

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

            MedalInfoModel.RankedCountryMedalInfo rankedInfo = new MedalInfoModel.RankedCountryMedalInfo();
            rankedInfo.name = countryMedalInfo.name;
            rankedInfo.rank = displayRank;
            rankedInfo.medalNumber = countryMedalInfo.medalNumber;

            rankedCountryMedalInfos.add(rankedInfo);

            rank++;
            lastInfo = countryMedalInfo;
        }

        return rankedCountryMedalInfos;
    }

    public static MedalInfoModel.CountryMedalInfo getCountryInfo(String name) throws Exception
    {
        MedalInfoModel.CountryMedalInfo info =
                MedalInfoManager.info.countries.stream()
                        .filter(x -> x.name.equals(name))
                        .findFirst()
                        .orElse(null);

        if (info == null)
        {
            throw new Exception("国家 " + name + " 不存在！");
        }

        return info;
    }

    public static MedalInfoModel.EventMedalInfo getEventInfo
            (MedalInfoModel.CountryMedalInfo country, String name) throws Exception
    {
        MedalInfoModel.EventMedalInfo info =
                country.events.stream()
                        .filter(x -> x.name.equals(name))
                        .findFirst()
                        .orElse(null);

        if (info == null)
        {
            throw new RuntimeException("国家 " + country.name + " 未参与项目 " + name + "！");
        }

        return info;
    }
}
